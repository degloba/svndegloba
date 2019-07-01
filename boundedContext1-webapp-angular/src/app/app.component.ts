import { Component, OnInit } from '@angular/core';
import {HotelDataService} from './providers/hotel-data.service';
import { Hotel } from './model/hotel';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['app.component.css'],
  providers: [HotelDataService]
})
export class AppComponent implements OnInit {
  title = 'Casino';
  
  hotels: Hotel[] = [];
  
  constructor() {}
  
  public ngOnInit() {
      
    }
  


}