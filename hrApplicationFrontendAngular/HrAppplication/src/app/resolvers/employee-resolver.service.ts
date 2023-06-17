import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { EmployeeApiService } from '../services/employee/employee-api.service';
import { EmployeeService } from '../services/employee/employee.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeResolverService {

  constructor(
    private employeeApiService: EmployeeApiService,
    private employeesService: EmployeeService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const employees = this.employeesService.getEmployees();

    if (employees.length === 0) {
      return this.employeeApiService.fetchAllEmployees();
    } else {
      return employees;
    }
  }
}
