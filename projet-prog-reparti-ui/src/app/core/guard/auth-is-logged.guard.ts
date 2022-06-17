import { Injectable } from "@angular/core";
import {CanActivate} from "@angular/router";
import {UrlService} from "../services/url.service";

@Injectable({
    providedIn: "root"
})
export class AuthIsLoggedGuard implements CanActivate {

    constructor(private urlService: UrlService) {
    }

    canActivate(): boolean {
      const username = sessionStorage.getItem("username");
      if (username != null) {
        return true;
      } else {
        this.urlService.navigateToConnection();
        return false;
      }
    }
}
