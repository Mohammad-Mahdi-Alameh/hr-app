import { Component, Input } from '@angular/core';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';

@Component({
  selector: 'app-expense-claims-item',
  templateUrl: './expense-claims-item.component.html',
  styleUrls: ['./expense-claims-item.component.css']
})
export class ExpenseClaimsItemComponent {
  @Input() expenseClaim: ExpenseClaim;
  @Input() index: number;

}
