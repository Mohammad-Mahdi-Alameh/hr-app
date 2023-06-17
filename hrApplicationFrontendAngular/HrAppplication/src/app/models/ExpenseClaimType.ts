import { ExpenseClaimDetail } from "./ExpenseClaimDetail";

export class ExpenseClaimType {
  constructor(
    public id: number,
    public name: string,
    public expenseClaimDetails: ExpenseClaimDetail[]
  ) {}
}
