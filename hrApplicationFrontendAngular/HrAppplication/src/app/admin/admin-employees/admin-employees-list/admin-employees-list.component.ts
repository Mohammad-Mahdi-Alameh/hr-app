import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Employee } from 'src/app/models/Employee.model';
import { EmployeeApiService } from 'src/app/services/employee/employee-api.service';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-admin-employees-list',
  templateUrl: './admin-employees-list.component.html',
  styleUrls: ['./admin-employees-list.component.css']
})
export class AdminEmployeesListComponent {
  isFetching = false;
  error = null;
  employees: Employee[];
  subscription: Subscription;

  constructor(private employeeService: EmployeeService,
    private employeeApiService: EmployeeApiService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.subscription = this.employeeService.employeesChanged
      .subscribe(
        (employees: Employee[]) => {
          this.employees = employees;
        }
      );
    this.fetchEmployees();
    this.employees = this.employeeService.getEmployees();
  }

  onNewEmployee() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  fetchEmployees() {
    this.isFetching = true;
    this.employeeApiService.fetchAllEmployees().subscribe(
      employees => {
        this.isFetching = false;
        // this.employees = employees;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }
}
