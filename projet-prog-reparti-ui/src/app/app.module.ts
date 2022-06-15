import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './elements/header/header.component';
import { FooterComponent } from './elements/footer/footer.component';
import { LayoutComponent } from './core/layout/layout.component';
import { registerLocaleData} from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { LearnersComponent } from './pages/learners/learners.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {MatInputModule} from "@angular/material/input";
import { ButtonComponent } from './shared/button/button.component';
import { MissionsComponent } from './pages/missions/missions.component';
import { SearchBarComponent } from './shared/search-bar/search-bar.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import { ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from "@angular/material/icon";

registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LayoutComponent,
    LearnersComponent,
    ButtonComponent,
    MissionsComponent,
    SearchBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatInputModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatIconModule
  ],
  providers: [{ provide: LOCALE_ID, useValue: "fr-FR" }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
