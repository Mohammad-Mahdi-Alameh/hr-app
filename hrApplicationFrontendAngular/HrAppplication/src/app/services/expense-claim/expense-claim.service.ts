import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimService {

  expenseClaimsChanged = new Subject<ExpenseClaim[]>();
  expenseClaimChanged = new Subject<ExpenseClaim>();
  index: number;

  private expenseClaims: ExpenseClaim[] = [];
  private expenseClaim: ExpenseClaim;
  constructor() { }

  setExpenseClaim(expenseClaim: ExpenseClaim) {
    this.expenseClaim = expenseClaim;
  }
  returnExpenseClaim() {
    return this.expenseClaim;
  }

  setExpenseClaims(expenseClaims: ExpenseClaim[]) {
    this.expenseClaims = expenseClaims;
    // this.expenseClaims.unshift(null);
    this.expenseClaimsChanged.next(this.expenseClaims.slice());
  }

  getExpenseClaims() {
    return this.expenseClaims.slice();
  }

  getExpenseClaim(id: number) {
    // return this.expenseClaims[index];
    return this.expenseClaims.find(expenseClaim => expenseClaim.id === id);
  }

  addExpenseClaim(expenseClaim: ExpenseClaim) {
    debugger
    // if(expenseClaim.leaves == null){
      // expenseClaim.leaves = [];
    // }
    this.expenseClaims.push(expenseClaim);
    this.expenseClaimsChanged.next(this.expenseClaims.slice());
  }

  updateExpenseClaim(id: number, newExpenseClaim: ExpenseClaim) {
    const index = this.expenseClaims.findIndex(expenseClaim => expenseClaim.id === id);
    this.expenseClaims[index] = newExpenseClaim;
    this.expenseClaim = newExpenseClaim;
    this.expenseClaimsChanged.next(this.expenseClaims.slice());
    this.expenseClaimChanged.next(this.expenseClaim);
  }

  deleteExpenseClaim(id: number) {
    const index = this.expenseClaims.findIndex(expenseClaim => expenseClaim.id === id);
    this.expenseClaims.splice(index, 1);
    this.expenseClaim = null;
    this.expenseClaimsChanged.next(this.expenseClaims.slice());
    this.expenseClaimChanged.next(this.expenseClaim);

  }
}
