import { Injectable, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Leave } from 'src/app/models/Leave.model';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {
  startedEditing = new Subject<number>();
  leavesChanged = new Subject<Leave[]>();
  leaveChanged = new Subject<Leave>();
  index: number;

  private leaves: Leave[] = [];
  private leave: Leave;
  constructor() { }

  setLeave(leave: Leave) {
    this.leave = leave;
  }
  returnLeave() {
    return this.leave;
  }

  setLeaves(leaves: Leave[]) {
    this.leaves = leaves;
    // this.leaves.unshift(null);
    this.leavesChanged.next(this.leaves.slice());
  }

  getLeaves() {
    return this.leaves.slice();
  }

  getLeave(id: number) {
    // return this.leaves[index];
    return this.leaves.find(leave => leave.id === id);
  }

  addLeave(leave: Leave) {
    debugger;
    this.leaves.push(leave);
    this.leavesChanged.next(this.leaves.slice());
  }

  updateLeave(id: number, newLeave: Leave) {
    const index = this.leaves.findIndex(leave => leave.id === id);
    this.leaves[index] = newLeave;
    this.leave = newLeave;
    this.leavesChanged.next(this.leaves.slice());
    this.leaveChanged.next(this.leave);
  }

  deleteLeave(id: number) {
    const index = this.leaves.findIndex(leave => leave.id === id);
    this.leaves.splice(index, 1);
    this.leave = null;
    this.leavesChanged.next(this.leaves.slice());
    this.leaveChanged.next(this.leave);

  }
}
