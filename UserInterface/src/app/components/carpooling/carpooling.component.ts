import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carpooling',
  templateUrl: './carpooling.component.html',
  styleUrls: ['./carpooling.component.css']
})
export class CarpoolingComponent implements OnInit {
  carSearchForm!: FormGroup; // Form group for car search form
  carpools: any[] = [];      // Array to store available carpools
  isLoggedIn: boolean = true;
  searchSubmitted: boolean = false; // Track if the search has been submitted

  constructor(
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.carSearchForm = this.formBuilder.group({
      source: ['', Validators.required],
      destination: ['', Validators.required]
    });
  }

  // Method to handle logout
  onLogout(): void {
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }

  navigateToBusTracking(): void {
    this.router.navigate(['/bus-tracking']);
  }

  navigateToNearestMetro(): void {
    this.router.navigate(['/nearest-metro']);
  }

  navigateToCarpooling(): void {
    this.router.navigate(['/carpooling']);
  }

  // Method to handle car search
  onSearch() {
    this.searchSubmitted = true;
    const { source, destination } = this.carSearchForm.value;

    // Mock data - replace with actual data fetching logic
    this.carpools = [
      { driverName: 'John Doe', carName: 'Toyota Camry', pickupLocation: 'Main St & 3rd Ave', date: new Date() },
      { driverName: 'Jane Smith', carName: 'Honda Accord', pickupLocation: 'Park Ave & 5th St', date: new Date() },
      { driverName: 'Mike Johnson', carName: 'Ford Focus', pickupLocation: 'Central Station', date: new Date() },
    ];
  }

  reserveCarpool(carpool: any) {
    console.log('Reserving carpool:', carpool);
    // Implement tracking logic here...
  }
}
