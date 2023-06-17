import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';
import { EmployeeLeavesComponent } from './employee/employee-leaves/employee-leaves.component';
import { AdminLeavesComponent } from './admin/admin-leaves/admin-leaves.component';
import { AdminEmployeesComponent } from './admin/admin-employees/admin-employees.component';
import { AdminExpenseClaimsComponent } from './admin/admin-expense-claims/admin-expense-claims.component';
import { EmployeeExpenseClaimsComponent } from './employee/employee-expense-claims/employee-expense-claims.component';
// import { LeaveStartComponent } from './leaves/leave-start/leave-start.component';
import { LeaveEditComponent } from './leaves/leave-edit/leave-edit.component';
// import { LeaveDetailComponent } from './leaves/leave-detail/leave-detail.component';
import { LeavesComponent } from './leaves/leaves.component';
// import { EmployeeGuard } from './route-guards/EmployeeGuard';
import { EmployeeGuardService } from './route-guards/employee-guard.service';
import { LoginComponent } from './login/login.component';
import { LeaveResolverService } from './resolvers/leave-resolver.service';
import { AdminGuardService } from './route-guards/admin-guard.service';
import { AuthGuardService } from './route-guards/auth-guard.service';
import { AdminEmployeesStartComponent } from './admin/admin-employees/admin-employees-start/admin-employees-start.component';
import { AdminEmployeesEditComponent } from './admin/admin-employees/admin-employees-edit/admin-employees-edit.component';
import { AdminEmployeesDetailComponent } from './admin/admin-employees/admin-employees-detail/admin-employees-detail.component';
import { EmployeeResolverService } from './resolvers/employee-resolver.service';
import { ExpenseClaimsStartComponent } from './expense-claims/expense-claims-start/expense-claims-start.component';
import { ExpenseClaimsEditComponent } from './expense-claims/expense-claims-edit/expense-claims-edit.component';
import { ExpenseClaimsDetailComponent } from './expense-claims/expense-claims-detail/expense-claims-detail.component';
import { ExpenseClaimResolverService } from './resolvers/expense-claim-resolver.service';
import { LeaveTypesPieChartComponent } from './leave-types-pie-chart/leave-types-pie-chart.component';
import { ExpenseClaimDetail } from './models/ExpenseClaimDetail';
import { ExpenseClaimTypesPieChartComponent } from './expense-claim-types-pie-chart/expense-claim-types-pie-chart.component';
const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuardService] },
  {
    path: 'employee', component: EmployeeComponent, canActivate: [EmployeeGuardService], children: [
      { path: '', redirectTo: '/employee/leaves', pathMatch: 'full' },
      {
        path: 'leaves',
        component: LeavesComponent
      },
      {
        path: 'expense-claims',
        component: EmployeeExpenseClaimsComponent,
        children: [
          { path: '', component: ExpenseClaimsStartComponent },
          { path: 'new', component: ExpenseClaimsEditComponent },
          {
            path: ':id',
            component: ExpenseClaimsDetailComponent,
            resolve: [ExpenseClaimResolverService]
          },
          {
            path: ':id/edit',
            component: ExpenseClaimsEditComponent,
            resolve: [ExpenseClaimResolverService]
          }
        ]
      }
    ]
  },
  {
    path: 'admin', component: AdminComponent, canActivate: [AdminGuardService], children: [
      { path: '', redirectTo: '/admin/employees', pathMatch: 'full' },
      {
        path: 'leaves',
        component: LeavesComponent
      },
      {
        path: 'employees',
        component: AdminEmployeesComponent,
        children: [
          { path: '', component: AdminEmployeesStartComponent },
          { path: 'new', component: AdminEmployeesEditComponent },
          {
            path: ':id',
            component: AdminEmployeesDetailComponent,
            resolve: [EmployeeResolverService]
          },
          {
            path: ':id/edit',
            component: AdminEmployeesEditComponent,
            resolve: [EmployeeResolverService]
          }
        ]
      },
      {
        path: 'expense-claims',
        component: AdminExpenseClaimsComponent,
        children: [
          { path: '', component: ExpenseClaimsStartComponent },
          {
            path: ':id',
            component: ExpenseClaimsDetailComponent,
            resolve: [ExpenseClaimResolverService]
          },
        ]
      },
      { path: 'leave-types-chart', component: LeaveTypesPieChartComponent },
      { path: 'expense-claim-types-chart', component: ExpenseClaimTypesPieChartComponent }
    ]
  },
  // { path: '**', redirectTo: '/user' }
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
