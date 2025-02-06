import { Component } from '@angular/core';
import { Employee } from '../model/employee';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html',
  styleUrls: ['./addemployee.component.css']
})
export class AddemployeeComponent {

  addData:any=<Employee>{}
  allCountry:any[]=[];
  isUpdated:boolean=false;

  constructor(private service:HttpService,
    private router:Router,
    private route:ActivatedRoute
  ){}

    ngOnInit():void{
      this.getDataFromBackend();
      this.getDataFromUrl();
      }

      getEmpByIdFromBackend(id:any){
      this.service.getEmpById(id)
      .subscribe((response)=>{
        // console.log(response);
        this.addData=response;
      })

      }

    getDataFromBackend(){
      this.service.getAllCountry()
     .subscribe((response:any)=>{
      // console.log(response);
      this.allCountry=response;
    });
  }

  onSubmit(){

    if(this.isUpdated){

      this.addData.updatedBy="Admin";
      this.addData.updatedDate=Date.now();
      this.service.updateEmpData(this.addData)
      .subscribe((response:any)=>{
        console.log(response);
        this.router.navigate([''])
      });
    }
    else{
    this.addData.createdBy="Admin";
    this.addData.createdDate=Date.now();
    this.addData.updatedBy="Admin";
    this.addData.updatedDate=Date.now();
    console.log(this.addData);

    this.service.postEmpData(this.addData)
    .subscribe((response)=>{
      console.log(response);
      this.router.navigate([''])
    })
    }
    
  }

  getDataFromUrl(){
    this.route.paramMap
    .subscribe((param)=>{
      // console.log(param.get("id"))
      this.getEmpByIdFromBackend(param.get("id"));
      this.isUpdated=true;
    })
  }
  

  
}
