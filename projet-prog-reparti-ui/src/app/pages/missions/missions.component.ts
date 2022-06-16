import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";

const actions = [
  {id: 1, libelle: "Petanque", date: "15-06-2022"},
  {id: 2, libelle: "Petanque", date: "15-06-2022"},
  {id: 3, libelle: "Petanque", date: "15-06-2022"}
];


@Component({
  selector: 'app-missions',
  templateUrl: './missions.component.html',
  styleUrls: ['./missions.component.css']
})
export class MissionsComponent implements OnInit {

  identity: string = "";

  actions = actions;

  constructor(private apiService: ApiService,
              private urlService: UrlService) { }

  ngOnInit(): void {
    this.setIdentity();
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

}
