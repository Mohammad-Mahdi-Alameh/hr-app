import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ExpenseClaimApiService } from 'src/app/services/expense-claim/expense-claim-api.service';
import { ExpenseClaimService } from 'src/app/services/expense-claim/expense-claim.service';

@Component({
  selector: 'app-expense-claims-list',
  templateUrl: './expense-claims-list.component.html',
  styleUrls: ['./expense-claims-list.component.css']
})
export class ExpenseClaimsListComponent {
  isFetching = false;
  error = null;
  expenseClaims: ExpenseClaim[];
  subscription: Subscription;
  isEmployee:Boolean;
  constructor(private expenseClaimService: ExpenseClaimService,
    private expenseClaimApiService: ExpenseClaimApiService,
    private authService:AuthService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.isEmployee = this.authService.getRole() === 'ROLE_EMPLOYEE' ? true : false;
    this.subscription = this.expenseClaimService.expenseClaimsChanged
      .subscribe(
        (expenseClaims: ExpenseClaim[]) => {
          this.expenseClaims = expenseClaims;
        }
      );
      if(this.isEmployee){
        this.fetchEmployeeExpenseClaims()
    }else{
      this.fetchAllExpenseClaims();
    }
    this.expenseClaims = this.expenseClaimService.getExpenseClaims();
  }
  //admin only
  fetchAllExpenseClaims() {
    // if(this.isEmployee){
    //   alert("Error : This method is only accessed by admin")
    //   this.authService.logout();
    // }
    this.isFetching = true;
    this.expenseClaimApiService.fetchAllExpenseClaims().subscribe(
      expenseClaims => {
        this.isFetching = false;
        // this.employees = employees;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }

  //employee only
  fetchEmployeeExpenseClaims() {
    // if(!this.isEmployee){
    //   alert("Error : This method is only accessed by employee")
    //   this.authService.logout();
    // }
    this.isFetching = true;
    this.expenseClaimApiService.fetchEmployeeExpenseClaims().subscribe(
      expenseClaims => {
        this.isFetching = false;
        // this.employees = employees;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }

  onNewExpenseClaim() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  fetchExpenseClaims() {
    this.isFetching = true;
    this.expenseClaimApiService.fetchAllExpenseClaims().subscribe(
      expenseClaims => {
        this.isFetching = false;
        // this.expenseClaims = expenseClaims;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }
}
