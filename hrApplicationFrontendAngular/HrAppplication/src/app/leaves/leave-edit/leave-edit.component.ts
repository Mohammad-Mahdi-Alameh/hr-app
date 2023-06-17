import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormArray, FormControl, Validators, NgForm } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { Leave } from 'src/app/models/Leave.model';
import { LeaveApiService } from 'src/app/services/leave/leave-api.service';
import { LeaveService } from 'src/app/services/leave/leave.service';

@Component({
  selector: 'app-leave-edit',
  templateUrl: './leave-edit.component.html',
  styleUrls: ['./leave-edit.component.css']
})
export class LeaveEditComponent implements OnInit {

  @ViewChild('f', { static: false }) leaveForm: NgForm;
  subscription: Subscription;
  editMode = false;
  editedItemIndex: number;
  editedItem: Leave;
  isFetching = false;
  error = null;

  constructor(private leaveService: LeaveService,
    private leaveApiService: LeaveApiService) { }
  ngOnInit() {
    this.subscription = this.leaveService.startedEditing
      .subscribe(
        (id: number) => {
          this.editedItemIndex = id;
          this.editMode = true;
          this.editedItem = this.leaveService.getLeave(id);
          this.leaveForm.setValue({
            fromDate: this.editedItem.fromDate,
            toDate: this.editedItem.toDate,
            note: this.editedItem.note,
            numberOfDays: this.editedItem.numberOfDays,
            leaveType: this.editedItem.leaveType
          })
        }
      );
  }

  onSubmit(form: NgForm) {
    const value = form.value;
    if ( value.fromDate >= value.toDate){
      alert("From date should be before to date!")
      return;
    }
    if (this.editMode) {
      debugger
      this.leaveApiService.updateLeave(this.editedItemIndex, value).subscribe(
        leave => {
          this.isFetching = false;
        },
        error => {
          this.isFetching = false;
          this.error = error.message;
          console.log(error);
        }
      );
    } else {
      this.leaveApiService.addLeave(value).subscribe(
        leave => {
          this.isFetching = false;
        },
        error => {
          this.isFetching = false;
          this.error = error.message;
          console.log(error);
        }
      );;
    }
    // const newLeave = new Leave(value.fromDate, value.amount);
    // if (this.editMode) {
      // this.leaveService.updateLeave(this.editedItemIndex, newLeave);
    // } else {
      // this.leaveService.addLeave(newLeave);
    // }
    this.editMode = false;
    form.reset();
  }

  onClear() {
    this.leaveForm.reset();
    this.editMode = false;
  }

  onDelete() {
    this.leaveApiService.deleteLeave(this.editedItemIndex).subscribe(
      response => {
        this.isFetching = false;
      },
      error => {
        this.isFetching = false;
        this.error = error.message;
        console.log(error);
      }
    );
    this.onClear();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
