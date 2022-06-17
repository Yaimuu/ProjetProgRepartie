import { Component, OnInit } from '@angular/core';
import {map, Observable, startWith} from "rxjs";
import {FormControl} from "@angular/forms";
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";

type option = {
  id: number;
  name: string;
};

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {

  myControl = new FormControl('');

  options: option[] = [];

  inputValue = "";

  filteredOptions: Observable<any[]> = new Observable<any[]>();

  constructor(private apiService: ApiService,
              public urlService: UrlService) { }

  ngOnInit(): void {
    this.getAllLearners();
  }

  getAllLearners() {
    this.apiService.getAllUsers().subscribe(
      data => {
        for (const user of data) {
          const line = {id: user.numUtil, name: user.surname + " " + user.forename}
          this.options.push(line);
        }
        this.filteredOptions = this.myControl.valueChanges.pipe(
          startWith(''),
          map(value => this._filter(value || '')),
        );
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

  private _filter(value: string): option[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.name.toLowerCase().includes(filterValue));
  }

  redirectToMissions(learnerId: number) {
      this.inputValue = "";
      this.urlService.navigateToMissions(learnerId);
  }

}
