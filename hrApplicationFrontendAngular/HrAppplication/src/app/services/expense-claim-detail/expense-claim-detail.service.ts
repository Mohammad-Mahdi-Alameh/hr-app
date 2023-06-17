import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ExpenseClaimDetail } from 'src/app/models/ExpenseClaimDetail';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimDetailService {

  expenseClaimDetailsChanged = new Subject<ExpenseClaimDetail[]>();

  private expenseClaimDetails: ExpenseClaimDetail[] = [];

  constructor() { }

  setExpenseClaimDetails(expenseClaimDetails: ExpenseClaimDetail[]) {
    this.expenseClaimDetails = expenseClaimDetails;
    this.expenseClaimDetailsChanged.next(this.expenseClaimDetails.slice());
  }

  getExpenseClaimDetails() {
    return this.expenseClaimDetails.slice();
  }

  getExpenseClaimDetail(index: number) {
    return this.expenseClaimDetails[index];
  }

  addExpenseClaimDetail(ExpenseClaimDetail: ExpenseClaimDetail) {
    this.expenseClaimDetails.push(ExpenseClaimDetail);
    this.expenseClaimDetailsChanged.next(this.expenseClaimDetails.slice());
  }

  updateExpenseClaimDetail(index: number, newRecipe: ExpenseClaimDetail) {
    this.expenseClaimDetails[index] = newRecipe;
    this.expenseClaimDetailsChanged.next(this.expenseClaimDetails.slice());
  }

  deleteExpenseClaimDetail(index: number) {
    this.expenseClaimDetails.splice(index, 1);
    this.expenseClaimDetailsChanged.next(this.expenseClaimDetails.slice());
  }
}
