import { Component, OnInit } from '@angular/core';
import {HotelDataService} from './providers/hotel-data.service';
import { Hotel } from './model/hotel';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

/**
// * <div class="descripcio">Pàgina principal</div>
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['app.component.css'],
  providers: [HotelDataService]
})
export class AppComponent implements OnInit {
  title = 'Casino';
  angForm: FormGroup;
  
  hotels: Hotel[] = [];
  
  constructor(private fb: FormBuilder) {
      this.createForm();
    }

    createForm() {
      this.angForm = this.fb.group({
        name: ["", Validators.required]
      });
    }
  
  public ngOnInit() {
      
    }
  


}