import { HttpClient } from '@angular/common/http';
import { Injectable, ɵisEnvironmentProviders } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  private apiUrl=environment.baseUrl+"/routes"

  constructor(private http:HttpClient) { }

  getAllRoutes():Observable<any>{
    return this.http.get(this.apiUrl);
  }

  deleteRouteById(id:number):Observable<any>{
    return this.http.delete(this.apiUrl+`/${id}`)
  }
}
