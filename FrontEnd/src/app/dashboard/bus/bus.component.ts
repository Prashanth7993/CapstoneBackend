import { Component, OnInit } from '@angular/core';
import { BusService } from '../services/bus.service';

@Component({
  selector: 'app-bus',
  templateUrl: './bus.component.html',
  styleUrl: './bus.component.css',
})
export class BusComponent implements OnInit {
  buses: any = [
    {
      id: 1,
      routeId: 101,
      capacity: 45,
      status: 'ACTIVE',
      realTimeData: [],
      busSchedules: [
        {
          id: 1,
          routeId: 101,
          departureTime: '2024-11-06T13:00:00',
          destinationArrivalTime: '2024-11-06T14:00:00',
          operatingStatus: true,
        },
        {
          id: 2,
          routeId: 101,
          departureTime: '2024-11-06T15:00:00',
          destinationArrivalTime: '2024-11-06T16:00:00',
          operatingStatus: true,
        },
      ],
    },
    {
      id: 2,
      routeId: 102,
      capacity: 50,
      status: 'ACTIVE',
      realTimeData: [],
      busSchedules: [
        {
          id: 3,
          routeId: 102,
          departureTime: '2024-11-06T14:00:00',
          destinationArrivalTime: '2024-11-06T15:00:00',
          operatingStatus: true,
        },
      ],
    },
    {
      id: 3,
      routeId: 103,
      capacity: 40,
      status: 'MAINTENANCE',
      realTimeData: [],
      busSchedules: [
        {
          id: 4,
          routeId: 103,
          departureTime: '2024-11-07T00:00:00',
          destinationArrivalTime: '2024-11-07T01:00:00',
          operatingStatus: false,
        },
        {
          id: 5,
          routeId: 103,
          departureTime: '2024-11-07T02:00:00',
          destinationArrivalTime: '2024-11-07T03:00:00',
          operatingStatus: false,
        },
      ],
    },
    {
      id: 4,
      routeId: 104,
      capacity: 55,
      status: 'ACTIVE',
      realTimeData: [],
      busSchedules: [
        {
          id: 6,
          routeId: 104,
          departureTime: '2024-11-07T12:00:00',
          destinationArrivalTime: '2024-11-07T13:00:00',
          operatingStatus: true,
        },
        {
          id: 7,
          routeId: 104,
          departureTime: '2024-11-07T14:00:00',
          destinationArrivalTime: '2024-11-07T15:00:00',
          operatingStatus: true,
        },
      ],
    },
  ];
  loading: boolean = false;
  constructor(private busService: BusService) {}
  ngOnInit(): void {
    // this.getAllBuses();
  }

  getAllBuses(): void {
    this.loading = true;
    this.busService.getAllBuses().subscribe({
      next: (data) => {
        console.log(data);
        this.buses = data;
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.loading = false;
      },
    });
  }

  deleteBus(id: number): void {
    this.loading = true;
    this.busService.deleteBusById(id).subscribe({
      next: (data) => {
        console.log(data);
        this.busService.getAllBuses();
        this.loading = false;
      },
      error: (error) => {
        console.log(error);
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      },
    });
  }
}
