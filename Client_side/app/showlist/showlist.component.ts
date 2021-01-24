import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-showlist',
  templateUrl: './showlist.component.html',
  styleUrls: ['./showlist.component.css']
})
export class ShowlistComponent implements OnInit {

  showlist:any;

  ngOnInit(): void {
    this.showlist=history.state.list;
    // this.showlist = [{equipmenttype:'tractor',servicetype:'fertilizing',rent:333,manufacturer:'farmtrac'},
    // {equipmenttype:'tractor',servicetype:'fertilizing',rent:333,manufacturer:'farmtrac'},
    // {equipmenttype:'tractor',servicetype:'fertilizing',rent:333,manufacturer:'farmtrac'}];
    console.log(this.showlist);     
  }

  constructor() { }

}

