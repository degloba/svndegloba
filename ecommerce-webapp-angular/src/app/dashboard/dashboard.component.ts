import { Component, OnInit, NgZone } from '@angular/core';
import { AuthService } from '../shared/services/auth.service';
import { Router } from '@angular/router';


import {HotelService} from '../providers/hotel.service';

import { Hotel } from '../model/hotel';

import {MockServerService} from "../providers/rxjs-component/mock-server-service/mockServer.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.component.scss'],
  providers: [HotelService, MockServerService]
})
export class DashboardComponent implements OnInit {

    hotels: Hotel[] = [];


  constructor(
    public authService: AuthService,
    private hotelService: HotelService,
    public router: Router,
    public ngZone: NgZone
  ) { }

  public ngOnInit() {
      this.hotelService
        .getAllHotels()
        .subscribe(
          (hotels) => {
            this.hotels = hotels;
		
          }
        );
    }
  
  onAddHotel(hotel) {
      this.hotelService
        .createHotel(hotel)
        .subscribe(
         (newHotel) => {
            this.hotels = this.hotels.concat(newHotel);
          }
        );
    }

  onToggleHotelComplete(hotel) {
       this.hotelService
        .updateHotel(hotel)
        .subscribe(
          (updatedHotel) => {
            hotel = updatedHotel;
         }
        );
    }

  onRemoveHotel(hotel) {
      this.hotelService
        .deleteHotelById(hotel.id)
        .subscribe(
          (_) => {
           this.hotels = this.hotels.filter((t) => t.id !== hotel.id);
         }
      );
    }


	goHotels() {
		this.router.navigate(['/hotel']);
	}

}

