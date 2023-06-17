import { Leave } from "./Leave.model";

export class LeaveType {
  constructor(public id: number, public employeeId: number, public leaves:Leave[] ) {
  }
}
