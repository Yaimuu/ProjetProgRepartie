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

  missions = [
    {
      id: 1,
      wording: "Christian",
      actions: [{
        wording: "Polytech"
      }]
    },
    {
      id: 2,
      wording: "Vial",
      actions: [{
        wording: "Lyon"
      },
        {
          wording: "Clermont"
        }]
    }
  ];

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
    this.validationBlockHidden = false;
  }

  getMissions() {
    for (const mission of this.missions){
      this.dataSource.data.push(new Mission(mission));
    }
    console.log(this.dataSource.data);
  }

  getCheckboxValue(missionId: number) {
    const userId = this.urlService.getLearnerId();

  }
}
