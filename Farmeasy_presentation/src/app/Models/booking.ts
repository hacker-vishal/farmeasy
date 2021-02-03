import { Timestamp } from "rxjs";

export class Booking {

    bookingid:number;
    email:string="";
    serviceprovider:string="";
    equipmenttype:string="";
    servicetype:string="";
    manufacturer:string="";
    dateofbooking:any;
    datefinish:any;
    location:string="";
    rent:any; 
    invalid:boolean=false;
}
