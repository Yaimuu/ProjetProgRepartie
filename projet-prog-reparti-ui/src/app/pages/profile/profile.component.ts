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
    surname: null,
    forename: null,
    email: null,
    nomUtil: null,
    password: null
  };

  error: string = "";

  constructor(private apiService: ApiService,
              public urlService: UrlService) { }

  ngOnInit(): void {
    this.getUser();
  }

  formValidation() {
    const regexp = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    if (!this.form.surname) {
      this.error = "Veuillez renseigner un nom."
      return false;
    } else if (!this.form.forename){
      this.error = "Veuillez renseigner un prénom."
      return false;
    } else if (!this.form.email){
      this.error = "Veuillez renseigner un email."
      return false;
    } else if (!this.form.email.match(regexp)) {
      this.error = "L'email saisie n'est pas valide.";
      return false;
    } else if (!this.form.nomUtil) {
      this.error = "Veuillez renseigner un nom d'utilisateur."
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

  getUser() {
    const userId = this.urlService.getLearnerId();
    this.apiService.getUser(userId).subscribe(
      (data) => {
        console.log(data);
        this.form = data;
      },
      err => {
        console.log(err.error.message);
      }
    );
  }

}
