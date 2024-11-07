import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrl: './feedback.component.css',
})
export class FeedbackComponent implements OnInit {
  feedbacks: any[] = [
    {
      id: 1,
      userId: 101,
      serviceType: 'Carpool',
      serviceId: 201,
      rating: 5,
      comment: 'Great ride, very punctual!',
      timestamp: '2024-03-15T14:30:00',
    },
    {
      id: 2,
      userId: 102,
      serviceType: 'Bus',
      serviceId: 301,
      rating: 4,
      comment: 'Food was good but slightly delayed',
      timestamp: '2024-03-15T15:45:00',
    },
    {
      id: 3,
      userId: 103,
      serviceType: 'Carpool',
      serviceId: 202,
      rating: 5,
      comment: 'Very comfortable ride',
      timestamp: '2024-03-15T16:20:00',
    },
    {
      id: 4,
      userId: 104,
      serviceType: 'Bus',
      serviceId: 302,
      rating: 3,
      comment: 'Average service',
      timestamp: '2024-03-15T17:10:00',
    },
  ];

  constructor() {}

  ngOnInit(): void {}

  getRatingClass(rating: number): string {
    switch (rating) {
      case 5:
        return 'text-green-600';
      case 4:
        return 'text-blue-600';
      case 3:
        return 'text-yellow-600';
      default:
        return 'text-red-600';
    }
  }

  onEdit(id: number): void {
    console.log('Edit feedback:', id);
  }

  onDelete(id: number): void {
    console.log('Delete feedback:', id);
  }

  formatDateTime(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }
}
