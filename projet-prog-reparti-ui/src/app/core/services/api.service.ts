import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl: string = "http://localhost:8080/api/";

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
    withCredentials: true
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
    return this.http.get(this.baseUrl + 'users', this.httpOptions);
  }

  getUser(id: number): Observable<any> {
    return this.http.get(this.baseUrl + 'user/' + id, this.httpOptions);
  }

  register(data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'auth/register', JSON.stringify(data), this.httpOptions);
  }

  removeUser(id: number): Observable<any> {
    return this.http.post(this.baseUrl + 'user/remove/' + id, {}, this.httpOptions);
  }

  updateUser(id: number, data: any): Observable<any> {
    return this.http.post(this.baseUrl + 'user/update/' + id, JSON.stringify(data), this.httpOptions);
  }

  getAllInscription(): Observable<any> {
    return this.http.get(this.baseUrl + 'inscriptions', this.httpOptions);
  }

  getInscriptionById(id: number): Observable<any> {
    return this.http.get(this.baseUrl + 'inscription/' + id, this.httpOptions);
  }

  getInscriptionsForUser(id: number): Observable<any> {
    return this.http.get(this.baseUrl + 'user/' + id + '/inscriptions', this.httpOptions);
  }

  getMissions(): Observable<any> {
    return this.http.get(this.baseUrl + 'missions', this.httpOptions);
  }

  registerUserToMission(userId: number, missionId: number): Observable<any> {
    return this.http.post(this.baseUrl + 'inscription/register/' + userId + '/' + missionId, {}, this.httpOptions);
  }

  removeInscription(inscriptionId: number) {
    return this.http.post(this.baseUrl + 'inscription/remove/' + inscriptionId, {}, this.httpOptions);
  }

  simulerAction(idAction: number, idInscription: number): Observable<any> {
    return this.http.get(this.baseUrl + 'action/simuler/' + idAction + '/' + idInscription, this.httpOptions);
  }

  getInscriptionsActions(idInscription: number): Observable<any> {
    return this.http.get(this.baseUrl + 'inscription/' + idInscription + '/actions', this.httpOptions);
  }

}

