export class ExpenseClaimDetail {
  constructor(
    public id: number,
    public expenseClaimId: number,
    public expenseClaimTypeId: number,
    public expenseClaimTypeName: string,
    public date: string,
    public total: number,
    public description: string
  ) { }
}
