import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { LeaveType } from 'src/app/models/LeaveType.model';
import { Ingredient } from 'src/app/shared/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class LeaveTypeService {

  leaveTypesChanged = new Subject<LeaveType[]>();

  private leaveTypes: LeaveType[] = [];

  constructor() { }

  setLeaveTypes(leaveTypes: LeaveType[]) {
    this.leaveTypes = leaveTypes;
    this.leaveTypesChanged.next(this.leaveTypes.slice());
  }

  getLeaveTypes() {
    return this.leaveTypes.slice();
  }

  getLeaveType(index: number) {
    return this.leaveTypes[index];
  }

  addLeaveType(LeaveType: LeaveType) {
    this.leaveTypes.push(LeaveType);
    this.leaveTypesChanged.next(this.leaveTypes.slice());
  }

  updateLeaveType(index: number, newRecipe: LeaveType) {
    this.leaveTypes[index] = newRecipe;
    this.leaveTypesChanged.next(this.leaveTypes.slice());
  }

  deleteLeaveType(index: number) {
    this.leaveTypes.splice(index, 1);
    this.leaveTypesChanged.next(this.leaveTypes.slice());
  }
}
