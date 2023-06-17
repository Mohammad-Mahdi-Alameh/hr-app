import { Component, ElementRef, ViewChild } from '@angular/core';
import { Chart } from 'chart.js';
import { Subscription } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import { ExpenseClaimDetail } from '../models/ExpenseClaimDetail';
import { ExpenseClaimDetailApiService } from '../services/expense-claim-detail/expense-claim-detail-api.service';
import { ExpenseClaimDetailService } from '../services/expense-claim-detail/expense-claim-detail.service';

@Component({
  selector: 'app-expense-claim-types-pie-chart',
  templateUrl: './expense-claim-types-pie-chart.component.html',
  styleUrls: ['./expense-claim-types-pie-chart.component.css']
})
export class ExpenseClaimTypesPieChartComponent {
  @ViewChild('expenseClaimTypeChart') chartCanvas: ElementRef;
  error = null;
  subscription: Subscription;
  expenseClaimDetails: ExpenseClaimDetail[];
  constructor(private expenseClaimDetailService: ExpenseClaimDetailService,
    private authService: AuthService,
    private expenseClaimDetailApiService: ExpenseClaimDetailApiService) { }
  fetchAllExpenseClaimDetails() {
    this.expenseClaimDetailApiService.fetchAllExpenseClaimDetails().subscribe(
      expenseClaimDetails => {
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
    this.fetchAllExpenseClaimDetails();
    this.expenseClaimDetails = this.expenseClaimDetailService.getExpenseClaimDetails();
    this.subscription = this.expenseClaimDetailService.expenseClaimDetailsChanged
      .subscribe(
        (expenseClaimDetails: ExpenseClaimDetail[]) => {
          debugger
          this.expenseClaimDetails = expenseClaimDetails;
          this.createPieChart();
        }
      );
  }

  createPieChart() {
    const chartCanvasCtx: CanvasRenderingContext2D = this.chartCanvas.nativeElement.getContext('2d');
    const expenseClaimDetailTypes = ['Hotel', 'Ticket', 'Food', 'Car Rental'];

    const expenseClaimDetailTypeCounts = expenseClaimDetailTypes.map(type => this.expenseClaimDetails.filter(expenseClaimDetail => expenseClaimDetail.expenseClaimTypeName === type).length);

    const totalExpenseClaimDetails = expenseClaimDetailTypeCounts.reduce((total, count) => total + count, 0);

    const percentages = expenseClaimDetailTypeCounts.map(count => (count / totalExpenseClaimDetails) * 100);

    new Chart(chartCanvasCtx, {
      type: 'pie',
      data: {
        labels: expenseClaimDetailTypes,
        datasets: [
          {
            data: percentages,
            backgroundColor: [
              '#FF6384', // Hotel
              '#36A2EB', // Ticket
              '#FFCE56', // Food
              '#4BC0C0'  // Car Rental
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
            text: 'Distribution of expense claims based on their types',
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
