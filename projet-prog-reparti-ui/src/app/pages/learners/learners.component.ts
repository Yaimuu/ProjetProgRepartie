import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Learner} from "../../core/models/learner.model";
import {UrlService} from "../../core/services/url.service";

const DATA: Learner[] = [
  {id: 1, surname: 'Hydrogen', forename: "Marie"},
  {id: 2, surname: 'Helium', forename: "Phillipe"},
  {id: 3, surname: 'Lithium', forename: "Jean-Paul"}
];

@Component({
  selector: 'app-learners',
  templateUrl: './learners.component.html',
  styleUrls: ['./learners.component.css']
})
export class LearnersComponent implements OnInit {

  numberOfLearners = 0;
  blockWithTipsHidden = false;

  // data source that fills table with information
  // dataSource: MatTableDataSource<Learner>;

  dataSource = DATA;

  displayedColumns = ["id", "surname", "forename", "missions", "modify", "select"];

  constructor(public urlService: UrlService) { }

  ngOnInit(): void {
    // TODO : this.dataSource = new MatTableDataSource<Learner>();
    this.numberOfLearners = this.dataSource.length;
  }

  hideBlockWithTips(): void {
    this.blockWithTipsHidden = true;
  }

}
