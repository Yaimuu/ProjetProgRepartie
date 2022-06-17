import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private apiService: ApiService,
              private urlService: UrlService) { }

  ngOnInit(): void {
  }

  logout() {
    this.apiService.logout().subscribe(
      data => {
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("role");
        this.urlService.navigateToHome();
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem("username");
    return !(user === null);
  }

  isUserAdmin() {
    const role = sessionStorage.getItem("role");
    return role != null && role == "admin";
  }

  getUserId() {
    return sessionStorage.getItem("id");
  }

}
