import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Learner} from "../../core/models/learner.model";
import {UrlService} from "../../core/services/url.service";
import {ApiService} from "../../core/services/api.service";

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

  constructor(public urlService: UrlService,
              private apiService: ApiService) { }

  ngOnInit(): void {
    if (!this.isUserAdmin()) {
      this.displayedColumns = this.displayedColumns.slice(0,-2);
    }
    this.getAllLearners();
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
        if (line.numUtil != null) {
          const selectedLearnerIndex = this.selectedLearners.indexOf(line.numUtil);
          // if learnerId is not selected
          if (selectedLearnerIndex == -1) {
            newData.push(line);
          } else {
            this.apiService.removeUser(line.numUtil).subscribe(
              () => {},
              err => {
                console.log(err.error);
              }
            );
          }
        }
      }
      this.dataSource.data = newData;
      this.numberOfLearners = newData.length;
      this.selectedLearners = [];
    }
  }

  getAllLearners() {
    this.apiService.getAllUsers().subscribe(
      data => {
        this.dataSource.data = data;
        this.numberOfLearners = data.length;
      },
      err => {
        console.log(err.error);
      }
    );
  }

    isUserAdmin() {
    const role = sessionStorage.getItem("role");
    return role != null && role == "admin";
  }

}
