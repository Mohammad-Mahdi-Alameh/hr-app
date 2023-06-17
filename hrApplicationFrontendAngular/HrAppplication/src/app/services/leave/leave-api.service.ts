import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Leave } from 'src/app/models/Leave.model';
import { LeaveService } from './leave.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LeaveApiService {

  constructor(private http: HttpClient, private leaveService: LeaveService) { }

  //admin functions

  fetchAllLeaves() {
    return this.http
      .get<Leave[]>(
        '/admin/leaves',
      )
      .pipe(
        tap(leaves => {
          console.log(leaves);
          debugger
          this.leaveService.setLeaves(leaves);
        })
      )
  }


  fetchLeaveById(id: number) {
    return this.http
      .get<Leave>(
        '/admin/leaves/' + id,
      )
      .pipe(
        tap(leave => {
          // debugger;
          console.log(leave);
          this.leaveService.setLeave(leave);
        })
      )
  }

  //employee functions

  fetchEmployeeLeaves() {
    return this.http
      .get<Leave[]>(
        '/employee/leaves',
      )
      .pipe(
        tap(leaves => {
          console.log(leaves);
          this.leaveService.setLeaves(leaves);
        })
      )
  }

  addLeave(leave: Leave) {
    return this.http
      .post<Leave>(
        '/employee/leaves', leave
      )
      .pipe(
        tap(leave => {
          debugger;
          console.log(leave);
          this.leaveService.addLeave(leave);
        })
      )
  }

  updateLeave(id: number, leave: Leave) {
    return this.http
      .put<Leave>(
        '/employee/leaves/' + id, leave
      )
      .pipe(
        tap(leave => {
          debugger;
          console.log(leave);
          this.leaveService.updateLeave(leave.id, leave);
        })
      )
  }

  deleteLeave(id: number) {
    return this.http
      .delete<Leave>(
        '/employee/leaves/' + id,
      )
      .pipe(
        tap(response => {
          debugger;
          console.log(response);
          this.leaveService.deleteLeave(id);
        })
      )
  }


}
