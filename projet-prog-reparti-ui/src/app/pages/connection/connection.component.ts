import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.css']
})
export class ConnectionComponent implements OnInit {

  form: any = {
    login: null,
    password: null
  };

  error = "";

  constructor(private apiService: ApiService,
              private urlService: UrlService) { }

  ngOnInit(): void {
  }

  formValidation() {
    if (!this.form.login) {
      this.error = "Veuillez renseigner votre nom d'utilisateur."
      return false;
    } else if (!this.form.password) {
      this.error = "Veuillez renseigner votre mot de passe."
      return false;
    } else {
      this.error = "";
      return true;
    }
  }

  signIn() {
    if (!this.formValidation()) {
      return;
    }

    this.apiService.login(this.form).subscribe(
      data => {
        this.urlService.navigateToHome();
      },
      err => {
        this.error = err.error.message;
      }
    );
  }

}
