import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BookService } from '../Services/book.service';
import { Booking } from '../Models/booking'
import { AppComponent } from '../app.component';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  bookData:any;
  msg:string;
  b:Booking;
  username:string;
  isLoggedIn:boolean;

  constructor(private bs:BookService, private ls:LoginService, private apc:AppComponent) { 
    this.b = new Booking();
  }

  ngOnInit(): void {

    this.ls.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
      this.ls.username.subscribe((data: string) => this.username = data);
      this.isLoggedIn = this.ls.isLoggedIn();


    this.bookData=history.state.book;
    console.log(this.bookData);
    this.b.serviceprovider = this.bookData.hostemail;
  }

  // rangeFormGroup = new FormGroup({  
  //   start: new FormControl(null, Validators.required),  
  //   end: new FormControl(null, Validators.required)  
  // }) 
  check()
  {
    console.log(this.b);
    //console.log("check checked!");
    this.bs.checkavailability(this.b).subscribe(
      (rsp:any)=>{
        if(rsp.status===1)
        {
          this.msg=rsp.message;
          console.log(rsp.status);
        }
      },
      (err)=>{console.log(JSON.stringify(err));
        this.msg="you got some error";

      });
  }
  
  logout() {
    this.apc.logout();
  }

}
