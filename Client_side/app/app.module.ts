import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import  { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { NgxWebstorageModule } from 'ngx-webstorage';
import { ToastrModule } from 'ngx-toastr';
import { TokenInterceptor } from './Models/token-interceptor';
//import { FontAwesomeModule } from '@fortawesome/fontawesome-free';
import { RouterModule, Routes } from '@angular/router';
/*
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { OtpverificationComponent } from './otpverification/otpverification.component';
import { SetnewpasswordComponent } from './setnewpassword/setnewpassword.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { ContactusComponent } from './contactus/contactus.component';
import { HelpComponent } from './help/help.component';
import { BlogComponent } from './blog/blog.component';
*/

const routes: Routes = [
  
  
]

@NgModule({
  declarations: [
      routingComponents,
    /*AppComponent,LoginComponent,SignupComponent,
  HomeComponent,ForgotpasswordComponent,OtpverificationComponent,SetnewpasswordComponent,
  AboutusComponent,ContactusComponent,HelpComponent,BlogComponent
  */
  ],
  
  imports: [
    BrowserModule,AppRoutingModule,NgbModule,FormsModule,HttpClientModule, 
    BrowserAnimationsModule,MatToolbarModule,MatInputModule,MatButtonModule,MatIconModule,ReactiveFormsModule,
    RouterModule.forRoot(routes),NgxWebstorageModule.forRoot(),ToastrModule.forRoot() //,FontAwesomeModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
