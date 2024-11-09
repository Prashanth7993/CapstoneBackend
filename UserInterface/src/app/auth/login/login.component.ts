import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    // Initialize the form with form controls and validators
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  // Method called on form submission
  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    // Extract email and password values from form
    const { email, password } = this.loginForm.value;

    // Call AuthService to handle login
    // this.authService.login(email, password).subscribe(
    //   (success) => {
    //     if (success) {
    //       // Redirect to the bus tracking page on successful login
    //       this.router.navigate(['/bus-tracking']);
    //     } else {
    //       // Show error message if login fails
    //       this.showErrorMessage('Email or password is incorrect');
    //     }
    //   },
    //   (error) => {
    //     // Handle any other errors (like network issues)
    //     this.showErrorMessage('An error occurred. Please try again.');
    //   }
    // );
  }

  // Method to display error messages using Snackbar
  private showErrorMessage(message: string): void {
    this.errorMessage = message;
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      verticalPosition: 'top'
    });
  }
}
