import { Department } from "./Department.model";
import { ExpenseClaim } from "./ExpenseClaim.model";
import { Leave } from "./Leave.model";
import { Role } from "./Roles.model";

export class Employee {
  constructor(
    public id: number,
    public username: string,
    public name: string,
    public address: string,
    public email: string,
    public password: string,
    public department: Department,
    public roles: Role[],
    public leaves: Leave[],
    public expenseClaims: ExpenseClaim[]) { }
}
