import { Component } from '@angular/core';
import { LeaveApiService } from '../services/leave/leave-api.service';
import { Subscription } from 'rxjs';
import { Leave } from '../models/Leave.model';
import { LeaveService } from '../services/leave/leave.service';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-leaves',
  templateUrl: './leaves.component.html',
  styleUrls: ['./leaves.component.css']
})
export class LeavesComponent {
  private subscription: Subscription;
  private isFetching = false;
  private error = null;
  filtered='';
  leaves: Leave[];
  isEmployee: Boolean ;
  widthValue = '';
  constructor(private leaveService: LeaveService,
    private authService: AuthService,
    private leaveApiService: LeaveApiService) { }

  ngOnInit() {
    debugger
    this.isEmployee = this.authService.getRole() === 'ROLE_EMPLOYEE' ? true : false;
    this.widthValue = this.isEmployee ? '80vw' : '100vw';
    if(this.isEmployee){
        this.fetchEmployeeLeaves()
    }else{
      this.fetchAllLeaves();
    }
    this.leaves = this.leaveService.getLeaves();
    this.subscription = this.leaveService.leavesChanged
      .subscribe(
        (leaves: Leave[]) => {
          this.leaves = leaves;
        }
      );
  }

  onEditItem(index: number) {
    this.leaveService.startedEditing.next(index);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  //admin only
  fetchAllLeaves() {
    // if(this.isEmployee){
    //   alert("Error : This method is only accessed by admin")
    //   this.authService.logout();
    // }
    this.isFetching = true;
    this.leaveApiService.fetchAllLeaves().subscribe(
      leaves => {
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

  //employee only
  fetchEmployeeLeaves() {
    // if(!this.isEmployee){
    //   alert("Error : This method is only accessed by employee")
    //   this.authService.logout();
    // }
    this.isFetching = true;
    this.leaveApiService.fetchEmployeeLeaves().subscribe(
      leaves => {
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
