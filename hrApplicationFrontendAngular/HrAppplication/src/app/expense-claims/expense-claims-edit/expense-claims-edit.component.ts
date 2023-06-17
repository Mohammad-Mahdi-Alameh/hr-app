import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ExpenseClaimApiService } from 'src/app/services/expense-claim/expense-claim-api.service';
import { ExpenseClaimService } from 'src/app/services/expense-claim/expense-claim.service';

@Component({
  selector: 'app-expense-claims-edit',
  templateUrl: './expense-claims-edit.component.html',
  styleUrls: ['./expense-claims-edit.component.css']
})
export class ExpenseClaimsEditComponent {
  id: number;
  editMode = false;
  isFetching = false;
  error = null;
  expenseClaimForm: FormGroup;
  get expenseClaimDetailRequests() {
    return (this.expenseClaimForm.get('expenseClaimDetailRequests') as FormArray).controls
  }
  constructor(
    private route: ActivatedRoute,
    private expenseClaimService: ExpenseClaimService,
    private router: Router,
    private expenseClaimApiService: ExpenseClaimApiService
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }

  onSubmit() {
    debugger;
    const value =this.expenseClaimForm.value;
    if (this.editMode) {
      debugger
      this.expenseClaimApiService.updateExpenseClaim(this.id, value).subscribe(
        expenseClaim => {
          this.isFetching = false;
        },
        error => {
          this.isFetching = false;
          this.error = error.message;
          console.log(error);
        }
      );
    } else {
      if(value.expenseClaimDetailRequests.length ==0){
        alert("Expense claim should have at least one detail ! ")
        return;
      }
      const totalAmount = value.expenseClaimDetailRequests.reduce((sum, payment) => sum + payment.total, 0);
      if(totalAmount != value.total){
        alert("Expense claim total payment should be equal to the sum of the detail payments! ")
        return;
      }
      this.expenseClaimApiService.addExpenseClaim(value).subscribe(
        expenseClaim => {
          this.isFetching = false;
        },
        error => {
          this.isFetching = false;
          this.error = error.message;
          console.log(error);
        }
      );;
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
  onAddDetail() {
    (<FormArray>this.expenseClaimForm.get('expenseClaimDetailRequests')).push(
      new FormGroup({
        'type': new FormControl(null, Validators.required),
        'date': new FormControl(null, Validators.required),
        'description': new FormControl(null, Validators.required),
        'total': new FormControl(null, [
          Validators.required,
          Validators.pattern(/^[1-9]+[0-9]*$/)
        ])
      })
    );
  }

  onDeleteDetail(index: number) {
    (<FormArray>this.expenseClaimForm.get('expenseClaimDetailRequests')).removeAt(index);
  }
  private initForm() {
    let date = '';
    let status = '';
    let total ;
    let description = '';
    let expenseClaimDetails = new FormArray([]);

    if (this.editMode) {
      const expenseClaim = this.expenseClaimService.getExpenseClaim(this.id);
      date = expenseClaim.date;
      status = expenseClaim.status;
      total = +expenseClaim.total;
      description = expenseClaim.description;
      if (expenseClaim['expenseClaimDetailRequests']) {
        for (let detail of expenseClaim.expenseClaimDetails) {
          expenseClaimDetails.push(
            new FormGroup({
              date: new FormControl(detail.date, Validators.required),
              description: new FormControl(detail.description, Validators.required),
              type: new FormControl(detail.expenseClaimTypeName, Validators.required),
              total: new FormControl(detail.total, [
                Validators.required,
                Validators.pattern(/^[1-9]+[0-9]*$/)
              ])
            })
          );
        }
      }
    }
    this.expenseClaimForm = new FormGroup({
      date: new FormControl(date, Validators.required),
      total: new FormControl(total, [
        Validators.required,
        Validators.pattern(/^[1-9]+[0-9]*$/)
      ]),
      description: new FormControl(description, Validators.required),
      status: new FormControl(status, Validators.required),
      expenseClaimDetailRequests: expenseClaimDetails
    });
    // else {
    //   this.expenseClaimForm = new FormGroup({
    //     username: new FormControl(expenseClaimUserName, Validators.required),
    //     password: new FormControl(expenseClaimPassword, Validators.required),
    //     name: new FormControl(expenseClaimName, Validators.required),
    //     address: new FormControl(expenseClaimAddress, Validators.required),
    //     email: new FormControl(expenseClaimEmail, Validators.required),
    //     departmentName: new FormControl(expenseClaimDepartmentName, Validators.required),
    //   });
    // }
  }
}
