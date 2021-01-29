import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-showlist',
  templateUrl: './showlist.component.html',
  styleUrls: ['./showlist.component.css']
})
export class ShowlistComponent implements OnInit {

  showlist:any;
  isListEmpty:boolean=true;
  dataToBook:any;

  ngOnInit(): void {
    this.showlist=history.state.list;
    // this.showlist = [{equipmenttype:'tractor',servicetype:'cultivating',rent:333,manufacturer:'farmtrac'},
    // {equipmenttype:'tractor',servicetype:'fertilizing',rent:333,manufacturer:'mahindra'},
    // {equipmenttype:'tractor',servicetype:'ploughing',rent:333,manufacturer:'swaraj'}];
    if(this.showlist!==null && this.showlist!==undefined)
    this.isListEmpty=false;

    console.log(this.showlist);  
    console.log(this.isListEmpty);    
  }

  constructor(private r:Router) { }

  goToBooking(item:any)
  {
    //console.log("hi");
    this.dataToBook=item;
    //console.log(this.dataToBook);
    this.r.navigate(['/book'],{state :{ book : this.dataToBook}});
  }

}

