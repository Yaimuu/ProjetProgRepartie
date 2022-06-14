import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HelloWorldComponent} from "./hello-world/hello-world.component";
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
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
