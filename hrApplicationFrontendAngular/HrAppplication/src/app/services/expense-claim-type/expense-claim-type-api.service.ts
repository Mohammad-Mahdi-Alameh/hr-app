import { Injectable } from '@angular/core';
import { ExpenseClaimTypeService } from './expense-claim-type.service';
import { HttpClient } from '@angular/common/http';
import { ExpenseClaimType } from 'src/app/models/ExpenseClaimType';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExpenseClaimTypeApiService {

  constructor(private http: HttpClient, private expenseClaimTypeService: ExpenseClaimTypeService) { }
  fetchAllExpenseClaimTypes() {
    return this.http
      .get<ExpenseClaimType[]>(
        '/admin/expense_claim_types',
      )
      .pipe(
        tap(expenseClaimTypes => {
          console.log(expenseClaimTypes);
          this.expenseClaimTypeService.setExpenseClaimTypes(expenseClaimTypes);
        })
      )
  }
}
