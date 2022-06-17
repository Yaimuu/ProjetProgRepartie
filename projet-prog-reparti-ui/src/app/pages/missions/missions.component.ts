import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";
import {UserInscription} from "../../core/models/UserInscription.model";
import {MatDialog} from "@angular/material/dialog";
import {IndicatorsComponent} from "../../shared/indicators/indicators.component";

@Component({
  selector: 'app-missions',
  templateUrl: './missions.component.html',
  styleUrls: ['./missions.component.css']
})
export class MissionsComponent implements OnInit {

  identity: string = "";

  userInscriptions: UserInscription[] = [];

  inscriptionsActions: any[] = [];

  hiddenCollapsible: Map<number, boolean> = new Map<number, boolean>();

  constructor(private apiService: ApiService,
              private urlService: UrlService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.setIdentity();
    this.getInscriptions();
  }

  getInscriptions() {
    const userId = this.urlService.getLearnerId();
    this.apiService.getInscriptionsForUser(userId).subscribe(
      (data: UserInscription[]) => {
        for (const inscription of data){
          this.userInscriptions.push(new UserInscription(inscription));
          if (inscription.id != null) {
            this.hiddenCollapsible.set(inscription.id, true);
          }
          this.apiService.getInscriptionsActions(Number(inscription.id)).subscribe(
            (data) => {
                this.inscriptionsActions.push(data);
            }
          );
        }
      },
      err => {
        console.log(err.error);
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
          console.log(err.error);
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

  canSimulate() {
    const role = sessionStorage.getItem("role");
    const id = Number(sessionStorage.getItem("id"));
    const urlId = this.urlService.getLearnerId();
    return (role != null && role == "admin") || (id != null && id == urlId);
  }

  getScore(inscriptionId: any, actionId: any) {
    if (this.inscriptionsActions.length > 0) {
      for(const inscription of this.inscriptionsActions) {
        for(const action of inscription) {
          if (inscriptionId == action.inscriptionByFkInscription.id && actionId == action.actionByFkAction.id) {
            return action.score;
          }
        }
      }
    }
  }

  isValid(inscriptionId: any, actionId: any) {
    if (this.inscriptionsActions.length > 0) {
      for(const inscription of this.inscriptionsActions) {
        for(const action of inscription) {
          if (inscriptionId == action.inscriptionByFkInscription.id && actionId == action.actionByFkAction.id) {
            return action.score >= action.actionByFkAction.scoreMinimum;
          }
        }
      }
    }
    return false;
  }

  isRemovable(inscriptionId: any) {
    const userId = Number(sessionStorage.getItem("id"));
    if (this.urlService.getLearnerId() != userId) {
      return false;
    }

    if (this.inscriptionsActions.length > 0) {
      for(const inscription of this.inscriptionsActions) {
        for(const action of inscription) {
          if (inscriptionId == action.inscriptionByFkInscription.id) {
            if (action.score != null) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  setScore(inscriptionId: any, actionId: any, score: number) {
    if (this.inscriptionsActions.length > 0) {
      for(const inscription of this.inscriptionsActions) {
        for(const action of inscription) {
          if (inscriptionId == action.inscriptionByFkInscription.id && actionId == action.actionByFkAction.id) {
            action.score = score;
          }
        }
      }
    }
  }


  removeInscription(inscriptionId: any) {
    this.apiService.removeInscription(inscriptionId).subscribe(
      () => {
        let newData = [];
        for(const userInscription of this.userInscriptions) {
          if(userInscription.id != inscriptionId) {
            newData.push(userInscription);
          }
        }
        this.userInscriptions = newData;
      },
      err => {
        alert(err.error);
        console.log(err.error);
      }
    );
  }

  simulerAction(actionId: any, inscriptionId: any, actionName: string) {
    this.apiService.simulerAction(actionId, inscriptionId).subscribe(
      (data) => {
        this.setScore(inscriptionId, actionId, data.score);
        this.openDialog(data, actionName);
      },
      err => {
        alert(err.error);
        console.log(err.error);
      }
    );
  }

  openDialog(simulation: any, simulationName: string) {
    const dialogRef = this.dialog.open(IndicatorsComponent, {
        data: {
          simulation,
          simulationName
      }
    });
  }

}
