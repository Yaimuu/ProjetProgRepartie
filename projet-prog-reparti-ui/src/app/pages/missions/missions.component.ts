import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";
import {UserInscription} from "../../core/models/UserInscription.model";

@Component({
  selector: 'app-missions',
  templateUrl: './missions.component.html',
  styleUrls: ['./missions.component.css']
})
export class MissionsComponent implements OnInit {

  identity: string = "";

  userInscriptions: UserInscription[] = [];

  hiddenCollapsible: Map<number, boolean> = new Map<number, boolean>();

  constructor(private apiService: ApiService,
              private urlService: UrlService) { }

  ngOnInit(): void {
    this.setIdentity();
    this.getInscriptions();
  }

  getInscriptions() {
    const userId = this.urlService.getLearnerId();
    this.apiService.getInscriptionsForUser(userId).subscribe(
      (data: UserInscription[]) => {
        console.log(data);
        for (const inscription of data){
          this.userInscriptions.push(new UserInscription(inscription));
            if (inscription.id != null) {
              this.hiddenCollapsible.set(inscription.id, true);
            }
        }
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  setIdentity() {
    const userId = this.urlService.getLearnerId();
    this.apiService.getUser(userId).subscribe(
      (data) => {
        this.identity = data.surname + " " + data.forename;
      },
        err => {
          console.log(err.error.message);
        }
    );
  }

  showCollapsible(missionId: number | undefined) {
    if(missionId != null) {
      const collapsibleState = this.hiddenCollapsible.get(missionId);
      this.hiddenCollapsible.set(missionId, !collapsibleState);
    }
  }

  isCollapsibleHidden(missionId: number | undefined) {
    if(missionId != null) {
      return this.hiddenCollapsible.get(missionId);
    }
    return false;
  }

  getAngleIcon(missionId: number | undefined) {
    if(missionId != null) {
      if (this.hiddenCollapsible.get(missionId)) {
        return "fas fa-angle-down";
      } else {
        return "fas fa-angle-up";
      }
    }
    return "fas fa-angle-down";
  }

  getCaretIcon(missionId: number | undefined) {
    if(missionId != null) {
      if (this.hiddenCollapsible.get(missionId)) {
        return "fas fa-caret-down";
      } else {
        return "fas fa-caret-up";
      }
    }
    return "fas fa-caret-down";
  }

}
