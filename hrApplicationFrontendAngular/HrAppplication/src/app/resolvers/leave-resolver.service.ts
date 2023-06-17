import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LeaveService } from '../services/leave/leave.service';
import { LeaveApiService } from '../services/leave/leave-api.service';

@Injectable({
  providedIn: 'root'
})
export class LeaveResolverService {

  constructor(
    private leaveApiService: LeaveApiService,
    private leavesService: LeaveService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const leaves = this.leavesService.getLeaves();

    if (leaves.length === 0) {
      return this.leaveApiService.fetchAllLeaves();
    } else {
      return leaves;
    }
  }
}
