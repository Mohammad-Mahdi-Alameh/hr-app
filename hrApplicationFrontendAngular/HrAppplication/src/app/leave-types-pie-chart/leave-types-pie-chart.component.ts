import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Chart } from 'chart.js/auto'
import { AuthService } from '../services/auth/auth.service';
import { LeaveApiService } from '../services/leave/leave-api.service';
import { LeaveService } from '../services/leave/leave.service';
import { Subscription } from 'rxjs';
import { Leave } from '../models/Leave.model';

@Component({
  selector: 'app-leave-types-pie-chart',
  templateUrl: './leave-types-pie-chart.component.html',
  styleUrls: ['./leave-types-pie-chart.component.css']
})
export class LeaveTypesPieChartComponent implements OnInit, AfterViewInit {
  @ViewChild('leaveTypeChart') chartCanvas: ElementRef;
  error = null;
  subscription: Subscription;
  leaves: Leave[];
  constructor(private leaveService: LeaveService,
    private authService: AuthService,
    private leaveApiService: LeaveApiService) { }
  fetchAllLeaves() {
    this.leaveApiService.fetchAllLeaves().subscribe(
      leaves => {
        // this.employees = employees;
      },
      error => {
        this.error = error.message;
        console.log(error);
      }
    );
  }
  ngAfterViewInit() {
    debugger
  }
  ngOnInit() {
    debugger
    this.fetchAllLeaves();
    this.leaves = this.leaveService.getLeaves();
    this.subscription = this.leaveService.leavesChanged
      .subscribe(
        (leaves: Leave[]) => {
          debugger
          this.leaves = leaves;
          this.createPieChart();
        }
      );
  }

  createPieChart() {
    const chartCanvasCtx: CanvasRenderingContext2D = this.chartCanvas.nativeElement.getContext('2d');
    const leaveTypes = ['Sick', 'Travel', 'Bussiness', 'Education'];

    const leaveTypeCounts = leaveTypes.map(type => this.leaves.filter(leave => leave.leaveType === type).length);

    const totalLeaves = leaveTypeCounts.reduce((total, count) => total + count, 0);

    const percentages = leaveTypeCounts.map(count => (count / totalLeaves) * 100);

    new Chart(chartCanvasCtx, {
      type: 'pie',
      data: {
        labels: leaveTypes,
        datasets: [
          {
            data: percentages,
            backgroundColor: [
              '#FF6384', // Sick
              '#36A2EB', // Travel
              '#FFCE56', // Bussiness
              '#4BC0C0'  // Education
            ]
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        aspectRatio: 1,
        plugins: {
          title: {
            display: true,
            text: 'Distribution of leaves based on their types',
            font: {
              size: 16
            }
          },
          legend: {
            position: 'right'
          }
        }
      }
    });
  }
}
