import { Component } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ExpenseClaimApiService } from 'src/app/services/expense-claim/expense-claim-api.service';
import { ExpenseClaimService } from 'src/app/services/expense-claim/expense-claim.service';

@Component({
  selector: 'app-expense-claims-detail',
  templateUrl: './expense-claims-detail.component.html',
  styleUrls: ['./expense-claims-detail.component.css']
})
export class ExpenseClaimsDetailComponent {
  expenseClaim: ExpenseClaim;
  id: number;
  isFetching = false;
  error = null;
  subscription: Subscription;
  isEmployee:Boolean;

  constructor(private expenseClaimService: ExpenseClaimService,
    private expenseClaimApiService: ExpenseClaimApiService,
    private authService:AuthService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.isEmployee = this.authService.getRole() === 'ROLE_EMPLOYEE' ? true : false;
    this.subscription = this.expenseClaimService.expenseClaimChanged
      .subscribe(
        (expenseClaim: ExpenseClaim) => {
          this.expenseClaim = expenseClaim;
        }
      );
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          // this.expenseClaim = this.expenseClaimService.getExpenseClaim(this.id);
          this.fetchExpenseClaim(this.id);
        }
      );
    // this.expenseClaim = this.expenseClaimService.returnExpenseClaim();
  }

  fetchExpenseClaim(id: number) {
    this.isFetching = true;
    this.expenseClaimApiService.fetchEmployeeExpenseClaimById(id).subscribe(
      expenseClaim => {
        this.isFetching = false;
        this.expenseClaim = expenseClaim
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }
  deleteExpenseClaim(id: number) {
    this.isFetching = true;
    this.expenseClaimApiService.deleteExpenseClaim(id).subscribe(
      response => {
        this.isFetching = false;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }
  onEditExpenseClaim() {
    this.router.navigate(['edit'], { relativeTo: this.route });
    // this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route});
  }

  onDeleteExpenseClaim() {
    this.deleteExpenseClaim(this.id);
    this.router.navigateByUrl('/employee/expense-claims');
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
