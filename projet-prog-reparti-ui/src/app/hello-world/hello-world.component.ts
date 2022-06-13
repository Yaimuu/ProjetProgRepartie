import {Component, OnInit} from '@angular/core';
import {HelloWorldService} from "./hello-world.service";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.css']
})
export class HelloWorldComponent implements OnInit {

  message: String = ""

  constructor(private helloWorldService: HelloWorldService) {
  }

  ngOnInit(): void {
    this.loadHelloWorld()
  }

  /**
   * Charge le message
   * @private
   */
  private loadHelloWorld(): void {
    this.helloWorldService.getHelloWorld().subscribe({
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

}
