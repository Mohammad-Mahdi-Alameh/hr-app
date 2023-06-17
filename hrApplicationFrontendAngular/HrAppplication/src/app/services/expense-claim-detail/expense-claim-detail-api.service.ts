import { Injectable } from '@angular/core';
import { ExpenseClaimDetailService } from './expense-claim-detail.service';
import { HttpClient } from '@angular/common/http';
import { ExpenseClaimDetail } from 'src/app/models/ExpenseClaimDetail';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimDetailApiService {

  constructor(private http: HttpClient, private expenseClaimDetailService: ExpenseClaimDetailService) { }
  fetchAllExpenseClaimDetails() {
    return this.http
      .get<ExpenseClaimDetail[]>(
        '/admin/expense_claim_details',
      )
      .pipe(
        tap(expenseClaimDetails => {
          console.log(expenseClaimDetails);
          this.expenseClaimDetailService.setExpenseClaimDetails(expenseClaimDetails);
        })
      )
  }
}
