import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {

  constructor(private http: HttpClient) {
  }

  /**
   * Get Hello World From API
   */
  public getHelloWorld(): Observable<HttpResponse<String>> {
    return this.http.get<string>(`/api/hello`, {observe: "response"})
  }
}
