import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ExpenseClaimApiService } from '../services/expense-claim/expense-claim-api.service';
import { ExpenseClaimService } from '../services/expense-claim/expense-claim.service';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimResolverService {

  constructor(
    private expenseClaimApiService: ExpenseClaimApiService,
    private expenseClaimsService: ExpenseClaimService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const expenseClaims = this.expenseClaimsService.getExpenseClaims();

    if (expenseClaims.length === 0) {
      return this.expenseClaimApiService.fetchAllExpenseClaims();
    } else {
      return expenseClaims;
    }
  }

}
