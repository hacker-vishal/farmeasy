import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';
import { ToastrService } from 'ngx-toastr';
import { Booking } from '../Models/booking';
import { Response } from '../Models/response';
import { BookService } from '../Services/book.service';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})

export class PaymentComponent implements OnInit {
  
  rent:any;
  b:Booking;
  username:string;

  //inject services required into constructor
  constructor(private r:Router, private t:ToastrService, private session:SessionStorageService,
    private bs:BookService, private ls:LoginService) { }

    //get booking data on load
  ngOnInit() {
    this.rent=this.session.get('rent');
    this.b=this.session.get('booking');
    this.username=this.ls.getUserName();
    this.b.email=this.username;
  }

  //make payment and if successful do book the service
  dopayment()
  {
    // console.log(this.b);
    this.bs.bookit(this.b).subscribe(
      (rsp:Response)=>{//console.log(rsp);
        if(rsp.status===1)
        {
          this.t.success("Payment received!");
          this.t.show(rsp.message);
          this.r.navigate(['/profile']);
        }
        else{
          this.t.warning("Payment failed!!!");
          this.t.info(rsp.message);
          //console.log(rsp.message);
        }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
        this.t.warning("Payment failed!!!");
      });
  }

}