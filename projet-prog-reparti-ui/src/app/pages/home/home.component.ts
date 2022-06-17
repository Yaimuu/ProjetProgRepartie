import {DatePipe} from '@angular/common';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [DatePipe]
})

export class HomeComponent implements OnInit {

  date: Date = new Date()
  currentDate: string | null = ""

  constructor(private datePipe: DatePipe) {

  }

  ngOnInit(): void {
    this.getCurrentDate();
  }

  public getCurrentDate() {
    this.currentDate = this.datePipe.transform(this.date, 'dd MMMM YYYY hh:mm:ss z');
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem("username");
    return !(user === null);
  }

  getUserId() {
    return sessionStorage.getItem("id");
  }

  isUserAdmin() {
    const role = sessionStorage.getItem("role");
    return role != null && role == "admin";
  }

}
