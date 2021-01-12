import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';



const routes: Routes = [
      { path:'app', component: AppComponent },
      { path:'home', component: HomeComponent },
      { path:'login', component: LoginComponent },
      { path:'signup', component: SignupComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [AppComponent,LoginComponent,SignupComponent,HomeComponent]
