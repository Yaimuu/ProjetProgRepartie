import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";
import {LocalStorage} from "@ngx-pwa/local-storage";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  form: any = {
    surname: null,
    forename: null,
    email: null,
    username: null,
    password: null
  };

  error = "";

  constructor(private apiService: ApiService,
              private urlService: UrlService,
              private localStorage: LocalStorage) { }

  ngOnInit(): void {
  }

  formValidation() {
    if (!this.form.surname) {
      this.error = "Veuillez renseigner votre nom."
      return false;
    } else if (!this.form.forename){
      this.error = "Veuillez renseigner votre prénom."
      return false;
    } else if (!this.form.email){
      this.error = "Veuillez renseigner votre email."
      return false;
    } else if (!this.form.username) {
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

  register() {
    if (!this.formValidation()) {
      return;
    }

    this.apiService.register(this.form).subscribe(
      data => {
        this.localStorage.setItem("ACCESS_TOKEN", data.accessToken);
        this.localStorage.setItem("USER_DATA", JSON.stringify(data));
        this.urlService.navigateToHome();
      },
      err => {
        this.error = err.error.message;
      }
    );
  }

}
