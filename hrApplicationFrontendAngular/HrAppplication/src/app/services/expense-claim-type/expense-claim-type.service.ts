import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ExpenseClaimType } from 'src/app/models/ExpenseClaimType';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimTypeService {

  expenseClaimTypesChanged = new Subject<ExpenseClaimType[]>();

  private expenseClaimTypes: ExpenseClaimType[] = [];

  constructor() { }

  setExpenseClaimTypes(expenseClaimTypes: ExpenseClaimType[]) {
    this.expenseClaimTypes = expenseClaimTypes;
    this.expenseClaimTypesChanged.next(this.expenseClaimTypes.slice());
  }

  getExpenseClaimTypes() {
    return this.expenseClaimTypes.slice();
  }

  getExpenseClaimType(index: number) {
    return this.expenseClaimTypes[index];
  }

  addExpenseClaimType(ExpenseClaimType: ExpenseClaimType) {
    this.expenseClaimTypes.push(ExpenseClaimType);
    this.expenseClaimTypesChanged.next(this.expenseClaimTypes.slice());
  }

  updateExpenseClaimType(index: number, newRecipe: ExpenseClaimType) {
    this.expenseClaimTypes[index] = newRecipe;
    this.expenseClaimTypesChanged.next(this.expenseClaimTypes.slice());
  }

  deleteExpenseClaimType(index: number) {
    this.expenseClaimTypes.splice(index, 1);
    this.expenseClaimTypesChanged.next(this.expenseClaimTypes.slice());
  }
}
