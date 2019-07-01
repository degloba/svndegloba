import { Injectable } from '@angular/core';
import {Hotel} from '../model/hotel';

import { ApiService } from './api.service';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class HotelDataService {

  
//Placeholder for last id so we can simulate
  // automatic incrementing of ids
  lastId: number = 0;

  // Placeholder for hotels
  hotels: Hotel[] = [];

  constructor(private api: ApiService) {
  }

//Simulate POST /hotels
  addTodo(hotel: Hotel): Observable<Hotel> {
    return this.api.createHotel(hotel);
  }

  // Simulate DELETE /hotels/:id
  deleteTodoById(hotelId: number): Observable<Hotel> {
    return this.api.deleteHotelById(hotelId);
  }

  // Simulate PUT /hotels/:id
  updateTodo(hotel: Hotel): Observable<Hotel> {
    return this.api.updateHotel(hotel);
  }

  // Simulate GET /hotels
  getAllHotels(): Observable<Hotel[]> {
    return this.api.getAllHotels();
  }

  // Simulate GET /hotels/:id
  getHotelById(hotelId: number): Observable<Hotel> {
    return this.api.getHotelById(hotelId);
  }

  // Toggle complete
  toggleHotelComplete(hotel: Hotel) {
      hotel.complete = !hotel.complete;
    return this.api.updateHotel(hotel);
  }
}
