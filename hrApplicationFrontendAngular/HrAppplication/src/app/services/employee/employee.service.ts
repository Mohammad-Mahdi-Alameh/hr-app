import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Employee } from 'src/app/models/Employee.model';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  employeesChanged = new Subject<Employee[]>();
  employeeChanged = new Subject<Employee>();
  index: number;

  private employees: Employee[] = [];
  private employee: Employee;
  constructor() { }

  setEmployee(employee: Employee) {
    this.employee = employee;
  }
  returnEmployee() {
    return this.employee;
  }

  setEmployees(employees: Employee[]) {
    this.employees = employees;
    // this.employees.unshift(null);
    this.employeesChanged.next(this.employees.slice());
  }

  getEmployees() {
    return this.employees.slice();
  }

  getEmployee(id: number) {
    // return this.employees[index];
    return this.employees.find(employee => employee.id === id);
  }

  addEmployee(employee: Employee) {
    if(employee.expenseClaims == null){
      employee.expenseClaims = [];
    }
    if(employee.leaves == null){
      employee.leaves = [];
    }
    this.employees.push(employee);
    this.employeesChanged.next(this.employees.slice());
  }

  updateEmployee(id: number, newEmployee: Employee) {
    const index = this.employees.findIndex(employee => employee.id === id);
    this.employees[index] = newEmployee;
    this.employee = newEmployee;
    this.employeesChanged.next(this.employees.slice());
    this.employeeChanged.next(this.employee);
  }

  deleteEmployee(id: number) {
    const index = this.employees.findIndex(employee => employee.id === id);
    this.employees.splice(index, 1);
    this.employee = null;
    this.employeesChanged.next(this.employees.slice());
    this.employeeChanged.next(this.employee);

  }
}
