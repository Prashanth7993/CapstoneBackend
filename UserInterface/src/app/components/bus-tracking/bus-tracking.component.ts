import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-bus-tracking',
  templateUrl: './bus-tracking.component.html',
  styleUrls: ['./bus-tracking.component.css']
})
export class BusTrackingComponent implements OnInit {
  busSearchForm!: FormGroup; // Form group for bus search form
  buses: any[] = [];         // Array to store available buses
  isLoggedIn: boolean = true;
  searchSubmitted: boolean = true; // Track if the search has been submitted

  constructor(
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    // Initialize the search form with required validators
    this.busSearchForm = this.formBuilder.group({
      source: ['', Validators.required],
      destination: ['', Validators.required]
    });
  }

  // Method to handle logout
  onLogout(): void {
    this.isLoggedIn = false;
    this.router.navigate(['/login']); // Redirect to login page after logout
  }

  // Navigate to bus tracking page
  navigateToBusTracking(): void {
    this.router.navigate(['/bus-tracking']);
  }

  // Navigate to nearest metro page
  navigateToNearestMetro(): void {
    this.router.navigate(['/nearest-metro']);
  }

  // Navigate to carpooling page
  navigateToCarpooling(): void {
    this.router.navigate(['/carpooling']);
  }

  // Method to handle bus search
  onSearch() {
    this.searchSubmitted = true;
    const { source, destination } = this.busSearchForm.value;

    // Mock data - replace with actual data fetching logic
    this.buses = [
      { number: 'A123', source, destination, time: '10:00 AM' },
      { number: 'B456', source, destination, time: '11:30 AM' },
      { number: 'C789', source, destination, time: '12:15 PM' },
    ];
  }

  trackBus(bus: any) {
    console.log('Tracking bus:', bus);
    // Implement tracking logic here
  }
}
