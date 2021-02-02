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
  isLoggedIn:boolean=false;
  maxDate: Date; 
  today:any;
  showdata:any;
  diff:any;
  total:any;
  isBookingAvailable:boolean=false;
  isShowDataAvailable:boolean=false;

  //injected the services required into constructor and created the object of booking
  constructor(private bs:BookService, private ls:LoginService, private t:ToastrService, 
    private session:SessionStorageService, private r:Router) { 
    this.b = new Booking();
  }

  //on loading of page, we will get data of booking
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

    //you can book upto 4 months from today
    let today = new Date();
    let month = today.getMonth();
    let nextMonth = (month === 11) ? 0 : month + 4;
    this.maxDate = new Date();
    this.maxDate.setMonth(nextMonth);

    this.today = today;
  }


  //checks the availability of booking slot and if available, shows the total rent you have to pay
  check()
  {
    //  console.log(this.b);
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
          this.b.email=this.bookData.email;
          this.b.equipmenttype=this.bookData.equipmenttype;
          this.b.location=this.bookData.location;
          this.b.manufacturer=this.bookData.manufacturer;
          this.b.rent=this.bookData.rent;
          this.b.serviceprovider=this.bookData.serviceprovider;
          this.b.servicetype=this.bookData.servicetype;
          this.b.invalid=false;

          this.session.set('rent',this.total);
          this.session.set('booking',this.b);
        }
        else{
          this.t.info(rsp.message);
          //console.log(rsp.message);
        }
      },
      (err)=>{console.log(JSON.stringify(err));
        this.t.error("You got some error!!!");
      });
  }

  //to book any service, you have to make payment, you will be redirected to payment page here
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
      this.r.navigate(['/login']);
    }
  }

  //calculates the difference between dateofbooking and datefinish to calculate total charges
  calculateDiff(b) {
    let date1:any = new Date(b.dateofbooking);
    let date2:any = new Date(b.datefinish);
    let diffDays:any = Math.ceil((date2 - date1) / (1000 * 60 * 60 * 24));
    return diffDays;
}

}
