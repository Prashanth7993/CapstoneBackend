import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerForm!: FormGroup; // Define registerForm as a FormGroup

  constructor(
    private formBuilder: FormBuilder,
    // private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Initialize the form group with form controls and validators
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]], // Assuming phone number is 10 digits
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['user', Validators.required] // Default role to 'user'
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      // Call the AuthService register method
      console.log("Valid Form")
      // this.authService.register(this.registerForm.value).subscribe(
      //   (response) => {
      //     console.log('Registration successful', response);
      //     this.router.navigate(['/login']); // Navigate to login page after successful registration
      //   },
      //   (error) => {
      //     console.error('Registration failed', error);
      //   }
      // );
    } else {
      console.log('Form is invalid');
    }
  }
}
