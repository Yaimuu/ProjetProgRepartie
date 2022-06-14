import { DatePipe } from '@angular/common';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [DatePipe]
})

export class HomeComponent implements OnInit {

  message: String = ""
  date: Date = new Date()
  currentDate: string | null = ""
  
  constructor(private homeService: HomeService, private datePipe: DatePipe) {
    
  }

  ngOnInit(): void {
    this.loadHome();
    this.getCurrentDate();
  }

  /**
   * Charge le message
   * @private
   */
  public loadHome(): void {
    this.homeService.getHome().subscribe({
      next: (response: HttpResponse<String>) => {
        if (response.ok && response.body) {
          this.message = response.body;
        } else {
          this.message = "erreur";
        }
      },
      error: (err: HttpErrorResponse) => {
        this.message = err.message
      }
    })
  }

  public getCurrentDate() {
    this.currentDate = this.datePipe.transform(this.date, 'dd MMMM YYYY');
  }

  

}
