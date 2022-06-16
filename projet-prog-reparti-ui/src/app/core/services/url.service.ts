import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import { Location } from "@angular/common";


@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private router: Router,
              private location: Location) { }

  navigateToMissions(userId: number) {
    this.router.navigate(["missions", userId], {
      queryParamsHandling: "merge", queryParams: { magicLinkToken: null, token: null }
    });
  }

  navigateToProfile(userId: number) {
    this.router.navigateByUrl("/", { skipLocationChange: true })
      .then(() => this.router.navigate(["profile", userId], {
        queryParamsHandling: "merge", queryParams: { magicLinkToken: null, token: null }
      }));
  }

  navigateToHome() {
    this.router.navigate(["home"], {
      queryParamsHandling: "merge", queryParams: { magicLinkToken: null, token: null }
    });
  }

  navigateToLearners() {
    this.router.navigate(["learners"], {
      queryParamsHandling: "merge", queryParams: { magicLinkToken: null, token: null }
    });
  }

  getLearnerId() {
    const urlParts = this.location.path().split("/");
    return Number(urlParts[2]);
  }
}
