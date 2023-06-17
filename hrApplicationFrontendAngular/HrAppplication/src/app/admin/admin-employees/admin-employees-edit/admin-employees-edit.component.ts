import { Component } from '@angular/core';
import { FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { EmployeeApiService } from 'src/app/services/employee/employee-api.service';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-admin-employees-edit',
  templateUrl: './admin-employees-edit.component.html',
  styleUrls: ['./admin-employees-edit.component.css']
})
export class AdminEmployeesEditComponent {
  id: number;
  editMode = false;
  isFetching = false;
  error = null;
  employeeForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private router: Router,
    private employeeApiService: EmployeeApiService
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
  }

  onSubmit() {
    if (this.editMode) {
      debugger
      this.employeeApiService.updateEmployee(this.id, this.employeeForm.value).subscribe(
        employee => {
          this.isFetching = false;
        },
        error => {
          this.isFetching = false;
          this.error = error.message;
          console.log(error);
        }
      );
    } else {
      this.employeeApiService.addEmployee(this.employeeForm.value).subscribe(
        employee => {
          this.isFetching = false;
        },
        error => {
          this.isFetching = false;
          this.error = error.message;
          console.log(error);
        }
      );;
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  private initForm() {
    let employeeName = '';
    let employeeUserName = '';
    let employeePassword = '';
    let employeeAddress = '';
    let employeeEmail = '';
    let employeeDepartmentName = '';

    if (this.editMode) {
      const employee = this.employeeService.getEmployee(this.id);
      employeeName = employee.name;
      employeeAddress = employee.address;
      employeeEmail = employee.email;
      employeeDepartmentName = employee.department.name;

      this.employeeForm = new FormGroup({
        name: new FormControl(employeeName, Validators.required),
        address: new FormControl(employeeAddress, Validators.required),
        email: new FormControl(employeeEmail, Validators.required),
        departmentName: new FormControl(employeeDepartmentName, Validators.required),
      });
    } else {
      this.employeeForm = new FormGroup({
        username: new FormControl(employeeUserName, Validators.required),
        password: new FormControl(employeePassword, Validators.required),
        name: new FormControl(employeeName, Validators.required),
        address: new FormControl(employeeAddress, Validators.required),
        email: new FormControl(employeeEmail, Validators.required),
        departmentName: new FormControl(employeeDepartmentName, Validators.required),
      });
    }
  }
}
