import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { DropdownDirective } from './shared/dropdown.directive';
import { AppRoutingModule } from './app-routing.module';
import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';
import { EmployeeHeaderComponent } from './employee/employee-header/employee-header.component';
import { AdminHeaderComponent } from './admin/admin-header/admin-header.component';
import { AdminLeavesComponent } from './admin/admin-leaves/admin-leaves.component';
import { AdminEmployeesComponent } from './admin/admin-employees/admin-employees.component';
import { AdminExpenseClaimsComponent } from './admin/admin-expense-claims/admin-expense-claims.component';
import { EmployeeExpenseClaimsComponent } from './employee/employee-expense-claims/employee-expense-claims.component';
import { EmployeeLeavesComponent } from './employee/employee-leaves/employee-leaves.component';
import { LeaveEditComponent } from './leaves/leave-edit/leave-edit.component';
import { LeavesComponent } from './leaves/leaves.component';
import { LoginComponent } from './login/login.component';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { AdminEmployeesItemComponent } from './admin/admin-employees/admin-employees-item/admin-employees-item.component';
import { AdminEmployeesStartComponent } from './admin/admin-employees/admin-employees-start/admin-employees-start.component';
import { AdminEmployeesEditComponent } from './admin/admin-employees/admin-employees-edit/admin-employees-edit.component';
import { AdminEmployeesDetailComponent } from './admin/admin-employees/admin-employees-detail/admin-employees-detail.component';
import { AdminEmployeesListComponent } from './admin/admin-employees/admin-employees-list/admin-employees-list.component';
import { FilterPipe } from './pipes/filter.pipe';
import { ExpenseClaimsComponent } from './expense-claims/expense-claims.component';
import { ExpenseClaimsDetailComponent } from './expense-claims/expense-claims-detail/expense-claims-detail.component';
import { ExpenseClaimsEditComponent } from './expense-claims/expense-claims-edit/expense-claims-edit.component';
import { ExpenseClaimsItemComponent } from './expense-claims/expense-claims-item/expense-claims-item.component';
import { ExpenseClaimsListComponent } from './expense-claims/expense-claims-list/expense-claims-list.component';
import { ExpenseClaimsStartComponent } from './expense-claims/expense-claims-start/expense-claims-start.component';
import { LeaveTypesPieChartComponent } from './leave-types-pie-chart/leave-types-pie-chart.component';
import { ExpenseClaimTypesPieChartComponent } from './expense-claim-types-pie-chart/expense-claim-types-pie-chart.component';


@NgModule({
  declarations: [
    AppComponent,
    DropdownDirective,
    EmployeeComponent,
    AdminComponent,
    EmployeeHeaderComponent,
    AdminHeaderComponent,
    AdminLeavesComponent,
    AdminEmployeesComponent,
    AdminExpenseClaimsComponent,
    EmployeeExpenseClaimsComponent,
    EmployeeLeavesComponent,
    LeaveEditComponent,
    LeavesComponent,
    LoginComponent,
    EmployeeComponent,
    AdminEmployeesItemComponent,
    AdminEmployeesStartComponent,
    AdminEmployeesEditComponent,
    AdminEmployeesDetailComponent,
    AdminEmployeesListComponent,
    FilterPipe,
    ExpenseClaimsComponent,
    ExpenseClaimsDetailComponent,
    ExpenseClaimsEditComponent,
    ExpenseClaimsItemComponent,
    ExpenseClaimsListComponent,
    ExpenseClaimsStartComponent,
    LeaveTypesPieChartComponent,
    ExpenseClaimTypesPieChartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
