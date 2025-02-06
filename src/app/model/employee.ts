export interface Employee{

    id:number,
    name:string,
    status:string,
    department:string,
    mobileno:string,
    emailid:string,
    createdBy:string,
    createdDate:Date,
    updatedBy:string,
    updatedDate:Date,
    salary:number,
    country:{
        cid:number,
        cname:string
    }
}