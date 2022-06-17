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

  login() {
    if (!this.formValidation()) {
      return;
    }

    this.apiService.login(this.form).subscribe(
      data => {
        sessionStorage.setItem("username", data.nomUtil);
        sessionStorage.setItem("surname", data.surname);
        sessionStorage.setItem("forename", data.forename);
        sessionStorage.setItem("role", data.role);
        sessionStorage.setItem("id", data.numUtil);
        this.urlService.navigateToHome();
      },
      err => {
        this.error = err.error.message;
      }
    );
  }

}
