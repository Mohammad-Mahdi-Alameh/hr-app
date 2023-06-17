import { Component, Input } from '@angular/core';
import { Employee } from 'src/app/models/Employee.model';

@Component({
  selector: 'app-admin-employees-item',
  templateUrl: './admin-employees-item.component.html',
  styleUrls: ['./admin-employees-item.component.css']
})
export class AdminEmployeesItemComponent {
  @Input() employee: Employee;
  @Input() index: number;
}
