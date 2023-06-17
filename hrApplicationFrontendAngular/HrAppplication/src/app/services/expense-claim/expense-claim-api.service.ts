import { Injectable } from '@angular/core';
import { ExpenseClaimService } from './expense-claim.service';
import { HttpClient } from '@angular/common/http';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimApiService {

  constructor(private http: HttpClient, private expenseClaimService: ExpenseClaimService) { }

  //admin functions

  fetchAllExpenseClaims() {
    return this.http
      .get<ExpenseClaim[]>(
        '/admin/expense_claims',
      )
      .pipe(
        tap(expenseClaims => {
          console.log(expenseClaims);
          this.expenseClaimService.setExpenseClaims(expenseClaims);
        })
      )
  }


  fetchExpenseClaimById(id: number) {
    return this.http
      .get<ExpenseClaim>(
        '/admin/expense_claims/' + id,
      )
      .pipe(
        tap(expenseClaim => {
          // debugger;
          console.log(expenseClaim);
          this.expenseClaimService.setExpenseClaim(expenseClaim);
        })
      )
  }

  //employee functions

  fetchEmployeeExpenseClaims() {
    return this.http
      .get<ExpenseClaim[]>(
        '/employee/expense_claims',
      )
      .pipe(
        tap(expenseClaims => {
          console.log(expenseClaims);
          this.expenseClaimService.setExpenseClaims(expenseClaims);
        })
      )
  }

  fetchEmployeeExpenseClaimById(id: number) {
    return this.http
      .get<ExpenseClaim>(
        '/employee/expense_claims/' + id,
      )
      .pipe(
        tap(expenseClaim => {
          // debugger;
          console.log(expenseClaim);
          this.expenseClaimService.setExpenseClaim(expenseClaim);
        })
      )
  }

  addExpenseClaim(expenseClaim: ExpenseClaim) {
    return this.http
      .post<ExpenseClaim>(
        '/employee/expense_claims', expenseClaim
      )
      .pipe(
        tap(expenseClaim => {
          debugger;
          console.log(expenseClaim);
          this.expenseClaimService.addExpenseClaim(expenseClaim);
        })
      )
  }

  updateExpenseClaim(id: number, expenseClaim: ExpenseClaim) {
    return this.http
      .put<ExpenseClaim>(
        '/employee/expense_claims/' + id, expenseClaim
      )
      .pipe(
        tap(expenseClaim => {
          debugger;
          console.log(expenseClaim);
          this.expenseClaimService.updateExpenseClaim(expenseClaim.id, expenseClaim);
        })
      )
  }

  deleteExpenseClaim(id: number) {
    return this.http
      .delete<ExpenseClaim>(
        '/employee/expense_claims/' + id,
      )
      .pipe(
        tap(response => {
          debugger;
          console.log(response);
          this.expenseClaimService.deleteExpenseClaim(id);
        })
      )
  }



}
