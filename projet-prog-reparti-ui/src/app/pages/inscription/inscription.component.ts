import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Learner} from "../../core/models/learner.model";
import {Mission} from "../../core/models/mission.model";
import {UserInscription} from "../../core/models/UserInscription.model";
import {UrlService} from "../../core/services/url.service";
import {ApiService} from "../../core/services/api.service";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  // data source that fills table with information
  dataSource: MatTableDataSource<Mission> = new MatTableDataSource<Mission>();

  displayedColumns = ["mission", "actions", "select"];

  selectedMissions:number[] = [];

  validationBlockHidden = true;

  constructor(public urlService: UrlService,
              private apiService: ApiService) { }

  ngOnInit(): void {
    this.getMissions();
  }

  selectMission(missionId: number) {
    const index = this.selectedMissions.indexOf(missionId);
    if (index > -1) {
      this.selectedMissions.splice(index, 1);
    } else {
      this.selectedMissions.push(missionId);
    }
  }

  register(missionId: number) {
    const userId = Number(sessionStorage.getItem("id"));
    this.apiService.registerUserToMission(userId, missionId).subscribe(
      () => {
        this.validationBlockHidden = false;
      },
      err => {
        console.log(err.error);
      }
    );
  }

  getMissions() {
    let newData: Mission[] = [];
    this.apiService.getMissions().subscribe(
      data => {
        for (const mission of data) {
          newData.push(new Mission(mission));
        }
        this.dataSource.data = newData;
      },
      err => {
        console.log(err.error);
      }
    );
  }
}
