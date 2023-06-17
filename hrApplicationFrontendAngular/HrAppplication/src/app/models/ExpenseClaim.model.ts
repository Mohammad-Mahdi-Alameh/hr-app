import { ExpenseClaimDetail } from "./ExpenseClaimDetail";

export class ExpenseClaim {
  constructor(
    public id: number,
    public employeeId: number,
    public employeeUserName: string,
    public date: string,
    public total: number,
    public description: string,
    public status: string,
    public expenseClaimDetails: ExpenseClaimDetail[]
  ) {}
}
