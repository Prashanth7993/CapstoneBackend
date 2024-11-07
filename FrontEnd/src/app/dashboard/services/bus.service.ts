import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class BusService {

  constructor(private http:HttpClient) { }

  private url=environment.baseUrl+'/buses';

  getAllBuses():Observable<any>{
    return this.http.get(this.url);
  }

  deleteBusById(id:number):Observable<any>{
    return this.http.delete(this.url+`/${id}`)
  }
}
