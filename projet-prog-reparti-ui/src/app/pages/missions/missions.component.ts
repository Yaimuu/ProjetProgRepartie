import { Component, OnInit } from '@angular/core';

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

  identity: string = "Lorem ipsum";

  actions = actions;

  constructor() { }

  ngOnInit(): void {
  }

  // TODO: getName from Id

}
