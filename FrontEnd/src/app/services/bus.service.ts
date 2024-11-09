import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BusService {
  private apiUrl = environment.baseUrl + '/buses';
  constructor(private http: HttpClient) {}

  getAllBuses(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
