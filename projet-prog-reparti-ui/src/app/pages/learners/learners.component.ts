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
  dataSource: MatTableDataSource<Learner> = new MatTableDataSource<Learner>();

  displayedColumns = ["id", "surname", "forename", "missions", "modify", "select"];

  selectedLearners:number[] = [];

  constructor(public urlService: UrlService) { }

  ngOnInit(): void {
    this.dataSource.data = DATA;
    this.numberOfLearners = this.dataSource.data.length;
  }

  hideBlockWithTips(): void {
    this.blockWithTipsHidden = true;
  }

  selectLearner(learnerId: number) {
    const index = this.selectedLearners.indexOf(learnerId);
    if (index > -1) {
      this.selectedLearners.splice(index, 1);
    } else {
      this.selectedLearners.push(learnerId);
    }
  }

  deleteSelectedLearners() {
    let newData = [];

    if(this.selectedLearners.length > 0) {
      for (let line of this.dataSource.data) {
        if (line.id != null) {
          const selectedLearnerIndex = this.selectedLearners.indexOf(line.id);
          // if learnerId is not selected
          if (selectedLearnerIndex == -1) {
            newData.push(line);
          } else {
            // TODO : update data base with API
          }
        }
      }
    }

    this.dataSource.data = newData;
    this.numberOfLearners = newData.length;
    this.selectedLearners = [];
  }

}
