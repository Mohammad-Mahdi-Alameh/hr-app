import { Injectable } from '@angular/core';
import { LeaveTypeService } from './leave-type.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LeaveTypeApiService {

  constructor(private http: HttpClient, private leaveTypeService: LeaveTypeService) { }
}
