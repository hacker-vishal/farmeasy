import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';
import { ToastrService } from 'ngx-toastr';
import { Response } from '../Models/response';
import { WishlistService } from '../Services/wishlist.service';
import { Wishlist } from '../Models/wishlist';
import { LoginService } from '../Services/login.service';
import { elementAt } from 'rxjs/operators';

@Component({
  selector: 'app-showlist',
  templateUrl: './showlist.component.html',
  styleUrls: ['./showlist.component.css']
})
export class ShowlistComponent implements OnInit {

  showlist:any;
  isListEmpty:boolean=true;
  isLoggedIn:boolean=false;
  dataToBook:any;
  w:Wishlist;
  username:string;
  eqtype:string;
  src:any;
  // istractor:boolean=true;
  // isCultivator:boolean=true;

  //inject services required into constructor
  constructor(private r:Router, private session:SessionStorageService, private t:ToastrService,
    private ws:WishlistService, private ls:LoginService) 
    { this.w = new Wishlist(); }

    //get list of services on loading of page
  ngOnInit(): void {
    this.username = this.ls.getUserName();
    //this.showlist=history.state.list;
    this.isLoggedIn = this.ls.isLoggedIn();
    this.showlist=this.session.get('list');
    this.eqtype = this.showlist[0].equipmenttype; 
    // for(let list of this.showlist)
    // {
      switch (this.eqtype) {
        case 'tractor':
            this.src='../../assets/tractor2.jpg';
            break;
        case 'cultivator':
          this.src='../../assets/tractors.jpg';
            break;
        case 'Backhoe':
          this.src='../../assets/tractor3.jpg';
            break;
        default:
          this.src='../../assets/accicon.jpg';
    }
    // }
    // console.log(this.showlist[0].equipmenttype);
    // this.showlist = [{equipmenttype:'tractor',servicetype:'cultivating',rent:333,manufacturer:'farmtrac'},
    // {equipmenttype:'tractor',servicetype:'fertilizing',rent:333,manufacturer:'mahindra'},
    // {equipmenttype:'tractor',servicetype:'ploughing',rent:333,manufacturer:'swaraj'}];
    
    if(this.showlist!==null && this.showlist!==undefined)
    this.isListEmpty=false;

    // console.log(this.showlist);  
    // console.log(this.isListEmpty);    
  }

  //redirect to book page on click of button of service you want
  goToBooking(item:any)
  {
    //console.log("hi");
    this.dataToBook=item;
    //console.log(this.dataToBook);
    this.session.remove('book');
    this.session.set('book',this.dataToBook);
    this.r.navigate(['/book']);
    // this.r.navigate(['/book'],{state :{ book : this.dataToBook}});
  }

  //add the desired service into wishlist
  addToWishlist(item:any)
  {//console.log(item);
    if(this.isLoggedIn)
    {
    this.w.email=this.username;
    this.w.serviceprovider=item.hostemail;
    this.w.equipmenttype=item.equipmenttype;
    this.w.location=item.location;
    this.w.manufacturer=item.manufacturer;
    this.w.rent=item.rent;
    this.w.servicetype=item.servicetype;
    // console.log(this.w.serviceprovider);
    // console.log(this.w);

    this.ws.addwishlist(this.w).subscribe(
      (rsp:Response)=>{

        if(rsp.status==1)
        {
          this.t.success(rsp.message);
          //console.log(rsp.message);
          //this.r.navigate(['/wishlist-cardview']);
        }
        else
        {
          this.t.info(rsp.message);
        }
    },
    (err)=>{//console.log(JSON.stringify(err));
      this.t.error("Some error occured!");
      this.t.info(JSON.stringify("You can see logs at C:/Users/Admin/AdvancedJAVA/farmease/logs/farmeasy.txt"));
    });
  }
  else{
    this.t.warning("You need to login first!!!");
    let url = '/showlist';
    this.r.navigate(['/login'], {state:{url:url}});
}
}
}

