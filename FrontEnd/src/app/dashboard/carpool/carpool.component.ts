import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
export interface CarPool {
  id: number;
  driverId: number;
  routeId: number;
  capacity: number;
  availableSeats: number;
  departureTime: string;
  pickupLocation: string;
  carpoolUsers: CarPoolUser[];
}

export interface CarPoolUser {
  id: number;
  userId: number;
  requestTime: string;
  carpool: any;
}
@Component({
  selector: 'app-carpool',
  templateUrl: './carpool.component.html',
  styleUrl: './carpool.component.css'
})
export class CarpoolComponent implements OnInit {

  carpools: any[] = [
    {
      id: 1,
      driverId: 101,
      routeId: 201,
      capacity: 4,
      availableSeats: 2,
      departureTime: '09:00 AM',
      pickupLocation: 'Central Station'
    },
    {
      id: 2,
      driverId: 102,
      routeId: 202,
      capacity: 3,
      availableSeats: 1,
      departureTime: '10:30 AM',
      pickupLocation: 'Airport Terminal'
    }
  ];

  constructor() { }

  ngOnInit(): void {
    // In real application, you would fetch data from a service
  }

  onEdit(id: number): void {
    console.log('Edit carpool:', id);
  }

  onDelete(id: number): void {
    console.log('Delete carpool:', id);
  }
}
