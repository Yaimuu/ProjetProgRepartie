import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private router: Router) { }

  navigateToMissions(userId: number) {
    this.router.navigate(["missions", userId], {
      queryParamsHandling: "merge", queryParams: { magicLinkToken: null, token: null }
    });
  }
}
