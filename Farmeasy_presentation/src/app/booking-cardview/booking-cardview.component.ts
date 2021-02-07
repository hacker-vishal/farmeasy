import { Component, OnInit } from '@angular/core';
import { BookService } from '../Services/book.service';
import { LoginService } from '../Services/login.service';
import { ToastrService } from 'ngx-toastr';
import { Response } from '../Models/response';

@Component({
  selector: 'app-booking-cardview',
  templateUrl: './booking-cardview.component.html',
  styleUrls: ['./booking-cardview.component.css']
})
export class BookingCardviewComponent implements OnInit {

  mybookings:any;
  username:string;
  isListEmpty:boolean=false;

  //inject the services required
  constructor(private bs: BookService, private loginService: LoginService, private t: ToastrService) { }

  //get the bookings done by user on page loading
  ngOnInit(): void { 
    this.username = this.loginService.getUserName();
    //console.log(this.username);
    this.showmybookings();
  }

  //finds the bookings done by particular user
  showmybookings()
  {
    this.bs.getbookings(this.username).subscribe(
      (data)=>{//console.log(data);
        if(data)
        {
          for(let d of data)
          {
            d.dateofbooking=new Date(d.dateofbooking).toLocaleDateString("en-us");
            d.datefinish=new Date(d.datefinish).toLocaleDateString("en-us");
            //console.log(d.datefinish);
          }
          
          if(data!==null && data!==undefined)
          this.isListEmpty=false;
          this.mybookings= data;
          //console.log(JSON.stringify(data)); 
        }
        else{
          this.t.info("You do not have any bookings yet!");
          this.isListEmpty=true;
        }
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("Some error occured! Seems you do not have any bookings yet!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
        window.location.reload();
      });
  }

  cancel(bid:any)
  {
    alert("You are about to cancel your booking!!!");
    //console.log(bid);
    this.bs.cancelmybooking(bid).subscribe(
      (rsp:Response)=>{
        if(rsp.status==1)
        {
          window.location.reload();
          this.t.success(rsp.message);
          this.t.info("You will get your money refunded within 3-4 working days!");
        }
        else
          this.t.warning(rsp.message);
      },
      (err)=>{//console.log(JSON.stringify(err));
        this.t.error("Some error occured! Could not perform this action!");
        this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
        window.location.reload();
      });
  }
}
