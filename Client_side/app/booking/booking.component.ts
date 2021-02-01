import { Component, OnInit } from '@angular/core';
import { BookService } from '../Services/book.service';
import { Booking } from '../Models/booking'
import { LoginService } from '../Services/login.service';
import { Response } from '../Models/response';
import { ToastrService } from 'ngx-toastr';
import { SessionStorageService } from 'angular-web-storage';
import { Router } from '@angular/router';

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
  isLoggedIn:boolean=false;
  maxDate: Date; 
  today:any;
  showdata:any;
  diff:any;
  total:any;
  isBookingAvailable:boolean=false;
  isShowDataAvailable:boolean=false;

  constructor(private bs:BookService, private ls:LoginService, private t:ToastrService, 
    private session:SessionStorageService, private r:Router) { 
    this.b = new Booking();
  }

  ngOnInit(): void {
    // this.isLoggedIn=false;
    this.bookData=this.session.get('book');
    // this.bookData=history.state.book;
    // console.log(this.bookData);
    this.isLoggedIn = this.ls.isLoggedIn();
    // console.log(this.isLoggedIn);

    if(this.bookData!==null && this.bookData!==undefined)
    {
      this.b.serviceprovider = this.bookData.hostemail;
      this.showdata = this.bookData;
      this.isShowDataAvailable=true;
    }

    if(this.b.serviceprovider==null || this.b.serviceprovider==undefined)
    this.b.serviceprovider=this.bookData.serviceprovider;

    let today = new Date();
    let month = today.getMonth();
    let nextMonth = (month === 11) ? 0 : month + 4;
    this.maxDate = new Date();
    this.maxDate.setMonth(nextMonth);

    this.today = today;
  }

  check()
  {
    // console.log(this.b);
    //console.log("check checked!");
    
    this.bs.checkavailability(this.b).subscribe(
      (rsp:Response)=>{//console.log(rsp);
        if(rsp.status===1)
        {
          this.t.show(rsp.message);
          //console.log(rsp.message);
          this.isBookingAvailable=true;
          this.diff=this.calculateDiff(this.b);
          this.total=this.diff*this.showdata.rent;
          //console.log("difference in dates: "+this.diff);
          //we habe to pass entire booking object to payment page
          //to confirm booking on successful payment
          this.b.email=this.username;
          this.b.equipmenttype=this.bookData.equipmenttype;
          this.b.location=this.bookData.location;
          this.b.manufacturer=this.bookData.manufacturer;
          this.b.rent=this.bookData.rent;
          this.b.serviceprovider=this.bookData.serviceprovider;
          this.b.servicetype=this.bookData.servicetype;
          this.session.set('rent',this.total);
          this.session.set('booking',this.b);
        }
        else{
          this.t.info(rsp.message);
          //console.log(rsp.message);
        }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");

      });
  }

  goToPayment()
  {
    // console.log(this.isLoggedIn);
    if(this.isLoggedIn===true)
    {
      this.r.navigate(['/payment']);
    }
    else
    {
      this.t.warning("You need to login first!!!");
    }
  }

  calculateDiff(b) {
    let date1:any = new Date(b.dateofbooking);
    let date2:any = new Date(b.datefinish);
    let diffDays:any = Math.ceil((date2 - date1) / (1000 * 60 * 60 * 24));
    return diffDays;
}

}
