import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  private url=environment.baseUrl+'/users';

  getAllUsers():Observable<any>{
    return this.http.get(this.url);
  }

  deleteUserById(id:number):Observable<any>{
    return this.http.delete(this.url+`/${id}`)
  }

}
