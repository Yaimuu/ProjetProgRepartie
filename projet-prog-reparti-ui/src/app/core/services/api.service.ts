import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl: string = "http://localhost:8080/api/";

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  login(data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'auth/login', JSON.stringify(data), this.httpOptions);
  }

  logout(): Observable<any> {
    return this.http.post(this.baseUrl + 'auth/logout', {}, this.httpOptions);
  }

  getAllUsers(): Observable<any> {
    return this.http.post(this.baseUrl + 'users', {}, this.httpOptions);
  }

  getUser(id: number): Observable<any> {
    return this.http.post(this.baseUrl + '/user/' + id, {}, this.httpOptions);
  }

  register(data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'signup', JSON.stringify(data), this.httpOptions);
  }

  update(data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'modify', JSON.stringify(data), this.httpOptions);
  }
}

