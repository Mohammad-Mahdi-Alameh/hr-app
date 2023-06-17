import { Component } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { Employee } from 'src/app/models/Employee.model';
import { ExpenseClaim } from 'src/app/models/ExpenseClaim.model';
import { EmployeeApiService } from 'src/app/services/employee/employee-api.service';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-admin-employees-detail',
  templateUrl: './admin-employees-detail.component.html',
  styleUrls: ['./admin-employees-detail.component.css']
})
export class AdminEmployeesDetailComponent {
  employee: Employee;
  id: number;
  isFetching = false;
  error = null;
  subscription: Subscription;

  constructor(private employeeService: EmployeeService,
    private employeeApiService: EmployeeApiService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    // debugger;
    this.subscription = this.employeeService.employeeChanged
      .subscribe(
        (employee: Employee) => {
          this.employee = employee;
        }
      );
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          // this.employee = this.employeeService.getEmployee(this.id);
          this.fetchEmployee(this.id);
        }
      );
    // this.employee = this.employeeService.returnEmployee();
  }

  fetchEmployee(id: number) {
    this.isFetching = true;
    this.employeeApiService.fetchEmployeeById(id).subscribe(
      employee => {
        this.isFetching = false;
        this.employee = employee
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }
  deleteEmployee(id: number) {
    this.isFetching = true;
    this.employeeApiService.deleteEmployee(id).subscribe(
      response => {
        this.isFetching = false;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
  }
  onEditEmployee() {
    this.router.navigate(['edit'], { relativeTo: this.route });
    // this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route});
  }

  onDeleteEmployee() {
    this.deleteEmployee(this.id);
    this.router.navigate(['/employees']);
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
