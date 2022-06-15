import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {LayoutComponent} from "./core/layout/layout.component";
import {LearnersComponent} from "./pages/learners/learners.component";
import {MissionsComponent} from "./pages/missions/missions.component";
import {ConnectionComponent} from "./pages/connection/connection.component";
import {RegistrationComponent} from "./pages/registration/registration.component";


const routes: Routes = [

  { path: "", redirectTo: "/home", pathMatch: "full" },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: "home", component: HomeComponent },
      { path: "missions/:userId", component: MissionsComponent },
      { path: "connection", component: ConnectionComponent },
      { path: "registration", component: RegistrationComponent },
      { path: "learners", component: LearnersComponent },
      // { path: "games", component: GamesComponent },
      // { path: "actions", component: DemandsPageComponent },
    ],

  },
  // {
  //   path: 'checkout',
  //   component: CheckoutViewComponent,
  //   canActivate: [AuthGuard]   // Our guard protects /checkout
  // },
  // {
  //   path: 'login', component: LoginComponent
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
