import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Chart } from 'chart.js/auto';

interface MetricCard {
  title: string;
  value: number;
  icon: string;
  trend: number;
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, AfterViewInit {
  // dashboard.component.ts

  @ViewChild('userChart') userChartCanvas!: ElementRef;
  @ViewChild('busChart') busChartCanvas!: ElementRef;
  
  userChart: Chart | undefined;
  busChart: Chart | undefined;

  metrics: MetricCard[] = [
    {
      title: 'Total Users',
      value: 15847,
      icon: 'fas fa-users',
      trend: 12.5
    },
    {
      title: 'Available Buses',
      value: 284,
      icon: 'fas fa-bus',
      trend: 8.2
    },
    {
      title: 'Active Routes',
      value: 156,
      icon: 'fas fa-route',
      trend: -2.4
    },
    {
      title: 'Daily Bookings',
      value: 1234,
      icon: 'fas fa-ticket-alt',
      trend: 15.7
    }
  ];

  constructor() {}

  ngOnInit() {
    // Initial setup if needed
  }

  ngAfterViewInit() {
    // Initialize charts after view is loaded
    this.initUserChart();
    this.initBusChart();
  }

  initUserChart() {
    const ctx = this.userChartCanvas.nativeElement.getContext('2d');
    this.userChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
          label: 'New Users',
          data: [650, 850, 920, 1100, 1200, 1350],
          borderColor: 'rgb(59, 130, 246)',
          tension: 0.4
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom'
          }
        }
      }
    });
  }

  initBusChart() {
    const ctx = this.busChartCanvas.nativeElement.getContext('2d');
    this.busChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        datasets: [{
          label: 'Bus Utilization',
          data: [85, 92, 88, 95, 90, 75, 70],
          backgroundColor: 'rgba(59, 130, 246, 0.5)'
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom'
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            max: 100
          }
        }
      }
    });
  }

  // Clean up charts when component is destroyed
  ngOnDestroy() {
    if (this.userChart) {
      this.userChart.destroy();
    }
    if (this.busChart) {
      this.busChart.destroy();
    }
  }

}
