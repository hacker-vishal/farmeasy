export class Hostuser {

    email:string="";
    equipmenttype:string="";
    manufacturer:string="";
    servicetype:string="";
    location:string=""; 
    rent:number=0;




constructor(equipmenttype, location)
{
    this.equipmenttype=equipmenttype;
    this.location=location;
    
}


}