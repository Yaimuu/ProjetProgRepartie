import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {LayoutComponent} from "./core/layout/layout.component";
import {LearnersComponent} from "./pages/learners/learners.component";
import {MissionsComponent} from "./pages/missions/missions.component";
import {ConnectionComponent} from "./pages/connection/connection.component";
import {RegistrationComponent} from "./pages/registration/registration.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {AuthIsLoggedGuard} from "./core/guard/auth-is-logged.guard";
import {AuthIsNotLoggedGuard} from "./core/guard/auth-is-not-logged.guard";
import {AdminGuard} from "./core/guard/admin.guard";
import {InscriptionComponent} from "./pages/inscription/inscription.component";


const routes: Routes = [

  { path: "", redirectTo: "/home", pathMatch: "full" },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: "home", component: HomeComponent },
      { path: "missions/:userId", component: MissionsComponent, canActivate: [AuthIsLoggedGuard] },
      { path: "connection", component: ConnectionComponent, canActivate: [AuthIsNotLoggedGuard] },
      { path: "registration", component: RegistrationComponent, canActivate: [AuthIsNotLoggedGuard] },
      { path: "learners", component: LearnersComponent, canActivate: [AuthIsLoggedGuard] },
      { path: "profile/:userId", component: ProfileComponent, canActivate: [AuthIsLoggedGuard, AdminGuard] },
      { path: "inscription/:userId", component: InscriptionComponent, canActivate: [AuthIsLoggedGuard] }
    ],

  },
  {
    path: "**",  // Angular router selects this route any time the requested URL doesn't match any router paths.
    redirectTo: "home",
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
