import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nearest-metro',
  templateUrl: './nearest-metro.component.html',
  styleUrls: ['./nearest-metro.component.css']
})
export class NearestMetroComponent implements OnInit {
  // busSearchForm!: FormGroup; // Form group for bus search form
  buses: any[] = [];         // Array to store available buses
  isLoggedIn: boolean = true;
  searchSubmitted: boolean = true; // Track if the search has been submitted

  constructor(
    private router: Router,
    // private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    // Initialize the search form with required validators
    // this.busSearchForm = this.formBuilder.group({
    //   source: ['', Validators.required],
    //   destination: ['', Validators.required]
    // });
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

}