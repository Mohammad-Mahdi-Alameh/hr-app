import { Injectable } from '@angular/core';
import { EmployeeService } from './employee.service';
import { HttpClient } from '@angular/common/http';
import { Employee } from 'src/app/models/Employee.model';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeApiService {

  constructor(private http: HttpClient, private employeeService: EmployeeService) { }

  fetchAllEmployees() {
    return this.http
      .get<Employee[]>(
        '/admin/employees',
      )
      .pipe(
        tap(employees => {
          console.log(employees);
          this.employeeService.setEmployees(employees);
        })
      )
  }

  fetchEmployeeById(id:number) {
    return this.http
      .get<Employee>(
        '/admin/employees/'+id,
      )
      .pipe(
        tap(employee => {
          // debugger;
          console.log(employee);
          this.employeeService.setEmployee(employee);
        })
      )
  }

  addEmployee(employee:Employee) {
    return this.http
      .post<Employee>(
        '/admin/employees',employee
      )
      .pipe(
        tap(employee => {
          debugger;
          console.log(employee);
          this.employeeService.addEmployee(employee);
        })
      )
  }

  updateEmployee(id:number,employee:Employee) {
    return this.http
      .put<Employee>(
        '/admin/employees/'+id,employee
      )
      .pipe(
        tap(employee => {
          debugger;
          console.log(employee);
          this.employeeService.updateEmployee(employee.id,employee);
        })
      )
  }

  deleteEmployee(id:number) {
    return this.http
      .delete<Employee>(
        '/admin/employees/'+id,
      )
      .pipe(
        tap(response => {
          debugger;
          console.log(response);
          this.employeeService.deleteEmployee(id);
        })
      )
  }


}
