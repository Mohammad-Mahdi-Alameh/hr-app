export class Leave {
  constructor(public id: number, public employeeId: number, public employeeUserName: string, public fromDate: string, public toDate: string, public numberOfDays: number, public note: string ,public leaveType: string) {
  }
}
