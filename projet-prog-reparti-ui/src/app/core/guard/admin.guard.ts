import { Injectable } from "@angular/core";
import {CanActivate} from "@angular/router";
import {UrlService} from "../services/url.service";

@Injectable({
  providedIn: "root"
})
export class AdminGuard implements CanActivate {

  constructor(private urlService: UrlService) {
  }

  canActivate(): boolean {
    const role = sessionStorage.getItem("role");
    if (role != null && role == "admin") {
      return true;
    } else {
      this.urlService.navigateToHome();
      return false;
    }
  }
}
