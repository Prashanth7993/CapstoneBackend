import { Component, OnInit } from '@angular/core';
import { RouteService } from '../../services/route.service';

export const DUMMY_ROUTES = [
  {
    id: 1,
    name: 'Miyapur To Koti',
    origin: 'Miyapur',
    destination: 'Koti',
    stops: [
      {
        id: 1,
        name: 'Chandanagar',
        latitude: 10.487,
        longitude: 9.93,
        route: null,
      },
      {
        id: 2,
        name: 'LalaGuda',
        latitude: 19.487,
        longitude: 90.93,
        route: null,
      },
    ],
    stations: [],
  },
  {
    id: 152,
    name: 'Patancheru To Koti',
    origin: 'Patancheru',
    destination: 'Koti',
    stops: [],
    stations: [],
  },
  {
    id: 202,
    name: 'Lingampally To Bowenpally',
    origin: 'Lingampally',
    destination: 'Bowenpally',
    stops: [
      {
        id: 202,
        name: 'Lingampally',
        latitude: 0.0,
        longitude: 0.0,
        route: null,
      },
      {
        id: 203,
        name: 'Bowenpally',
        latitude: 0.0,
        longitude: 0.0,
        route: null,
      },
    ],
    stations: [
      {
        id: 152,
        name: 'Miyapur Jn',
        latitude: 73.930489,
        longitude: 18.9309,
        route: null,
      },
    ],
  },
];

@Component({
  selector: 'app-routes',
  templateUrl: './routes.component.html',
  styleUrl: './routes.component.css',
})
export class RoutesComponent implements OnInit {
  routes: any[] = [];
  expandedRoutes: boolean[] = [];
  isLoading: boolean = false;

  constructor(private routeService: RouteService) {}

  ngOnInit() {
    // this.routes = DUMMY_ROUTES;
    this.getAllRoutes()
    this.expandedRoutes = new Array(this.routes.length).fill(false);
  }

  toggleRouteDetails(index: number) {
    this.expandedRoutes[index] = !this.expandedRoutes[index];
  }

  getAllRoutes(): any {
    this.isLoading = true;
    this.routeService.getAllRoutes().subscribe({
      next: (data) => {
        console.log(data);
        this.routes = data;
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }

  addNewRoute() {
    console.log('Adding new route');
  }

  deleteRoute(id: number) {
    this.isLoading = true;
    console.log('Deleting route:', id);
    this.routeService.deleteRouteById(id).subscribe({
      next: (data) => {
        console.log(data);
        this.getAllRoutes();
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }

  getStopsCount(route: any): number {
    return route.stops.length;
  }

  getStationsCount(route: any): number {
    return route.stations.length;
  }
}
