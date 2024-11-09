import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'https://your-backend-api-url.com'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  // Login method
  login(email: string, password: string): Observable<boolean> {
    const url = `${this.apiUrl}/login`;
    const body = { email, password };

    return this.http.post<any>(url, body).pipe(
      map(response => {
        // Assuming the API returns a token on successful login
        if (response && response.token) {
          // Store the token in local storage
          localStorage.setItem('authToken', response.token);
          return true;
        }
        return false;
      }),
      catchError(error => {
        console.error('Login failed', error);
        return of(false); // Return false on login failure
      })
    );
  }

  // Registration method
  register(userData: { name: string; phone: string; email: string; password: string; role: string }): Observable<any> {
    const url = `${this.apiUrl}/register`;

    return this.http.post<any>(url, userData).pipe(
      map(response => {
        // Handle successful registration, if needed
        return response;
      }),
      catchError(error => {
        console.error('Registration failed', error);
        return of({ success: false, message: 'Registration failed' });
      })
    );
  }

  // Method to check if user is logged in by checking token existence
  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken');
  }

  // Method to log out user
  logout(): void {
    // Clear token from local storage
    localStorage.removeItem('authToken');
  }
}
