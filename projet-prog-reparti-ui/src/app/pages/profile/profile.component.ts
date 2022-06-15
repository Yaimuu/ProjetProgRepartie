import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../core/services/api.service";
import {UrlService} from "../../core/services/url.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  form: any = {
    surname: "Titouan",
    forename: "Anthony",
    email: "yanis@ouledmoussa.com",
    username: "Iskander",
    password: "isi3"
  };

  error: string = "";

  constructor(private apiService: ApiService,
              public urlService: UrlService) { }

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

  update() {
    if (!this.formValidation()) {
      return;
    }

    this.apiService.update(this.form).subscribe(
      data => {
        this.urlService.navigateToLearners();
      },
      err => {
        this.error = err.error.message;
      }
    );
  }

}
