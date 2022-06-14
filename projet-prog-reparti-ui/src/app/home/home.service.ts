import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  /**
   * Get Home From API
   */
   public getHome(): Observable<HttpResponse<String>> {
    return this.http.get<string>(`/api/home`, {observe: "response"})
  }
}
