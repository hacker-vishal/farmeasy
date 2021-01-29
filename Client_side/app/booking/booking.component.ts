import { Component, OnInit } from '@angular/core';
import { BookService } from '../Services/book.service';
import { Booking } from '../Models/booking'
import { Response } from '../Models/response';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  bookData:any;
  msg:string;
  b:Booking;

  constructor(private bs:BookService) { 
    this.b = { bookingid:"",email:"",serviceprovider:"",equipmenttype:"",servicetype:"",
      dateofbooking:"",datefinish:"",location:"",rent:"" };
  }

  ngOnInit(): void {
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
      (r:any)=>{
        if(r.status===1)
        {
          console.log("hey!");
          this.msg=r.message;
          console.log(r.status);
        }
      },
      (err)=>{console.log(JSON.stringify(err));
        this.msg="you got some error";

      });
  }
  
}
