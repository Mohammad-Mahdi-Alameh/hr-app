import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Employee } from 'src/app/models/Employee.model';
import { EmployeeApiService } from 'src/app/services/employee/employee-api.service';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-admin-employees',
  templateUrl: './admin-employees.component.html',
  styleUrls: ['./admin-employees.component.css']
})
export class AdminEmployeesComponent {


}
