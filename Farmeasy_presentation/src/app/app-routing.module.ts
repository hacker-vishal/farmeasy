import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { OtpverificationComponent } from './otpverification/otpverification.component';
import { SetnewpasswordComponent } from './setnewpassword/setnewpassword.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { ContactusComponent } from './contactus/contactus.component';
import { HelpComponent } from './help/help.component';
import { BlogComponent } from './blog/blog.component';
import { ProfileComponent } from './profile/profile.component';
import { ShowlistComponent } from './showlist/showlist.component';
import { HostComponent } from './host/host.component';
import { AuthGuard } from './Services/auth.guard';
import { BookingComponent } from './booking/booking.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { BookingCardviewComponent } from './booking-cardview/booking-cardview.component';
import { WishlistCardviewComponent } from './wishlist-cardview/wishlist-cardview.component';
import { PaymentComponent } from './payment/payment.component';


const routes: Routes = [
      { path:'', redirectTo:'home', pathMatch: 'full' },
      { path:'home', component: HomeComponent },
      { path:'login', component: LoginComponent },
      { path:'signup', component: SignupComponent },
      { path:'forgotpassword', component: ForgotpasswordComponent },
      { path:'otpverification', component: OtpverificationComponent },
      { path:'setnewpassword', component: SetnewpasswordComponent },
      { path:'aboutus', component: AboutusComponent },
      { path:'contactus', component: ContactusComponent },
      { path:'help', component: HelpComponent },
      { path:'blog', component: BlogComponent },
      { path:'host', component: HostComponent },
      { path:'book', component: BookingComponent },
      { path:'showlist', component: ShowlistComponent },
      { path:'user-menu', component: UserMenuComponent },
      { path:'booking', component: BookingComponent },
      { path:'payment', component: PaymentComponent },
      { path:'booking-cardview', component: BookingCardviewComponent },
      { path:'wishlist-cardview', component: WishlistCardviewComponent },
      { path:'profile', component: ProfileComponent },
      { path:'profile/: name', component: ProfileComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [AppComponent,LoginComponent,SignupComponent,
  HomeComponent,ForgotpasswordComponent,OtpverificationComponent,SetnewpasswordComponent,
  AboutusComponent,ContactusComponent,HelpComponent,BlogComponent,ProfileComponent,ShowlistComponent,
  HostComponent,BookingComponent,UserMenuComponent,WishlistCardviewComponent,BookingCardviewComponent, PaymentComponent
  ]
