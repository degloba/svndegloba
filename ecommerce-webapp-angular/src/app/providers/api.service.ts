import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

import { Http, Response } from '@angular/http';
import { Hotel } from '../model/hotel';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

const API_URL = environment.apiUrl;

@Injectable()
export class ApiService {

  constructor(private http: Http) { }
  
  private handleError (error: Response | any) {
      console.error('ApiService::handleError', error);
      return Observable.throw(error);
    }
  
  // API: GET /hotels
  public getAllHotels(): Observable<Hotel[]> {
      return this.http
        .get(API_URL)
        .map(response => {
          const hotels = response.json();
          return hotels.items.map((hotel) => new Hotel(hotel));
        })
        .catch(this.handleError);
    }

  // API: POST /hotels
  public createHotel(hotel: Hotel): Observable<Hotel> {
      return this.http
        .post(API_URL + '/hotels', hotel)
        .map(response => {
          return new Hotel(response.json());
        })
        .catch(this.handleError);
    }

  // API: GET /hotels/:id
  public getHotelById(hotelId: number): Observable<Hotel> {
      return this.http
        .get(API_URL + '/hotels/' + hotelId)
        .map(response => {
          return new Hotel(response.json());
        })
        .catch(this.handleError);
    }

  // API: PUT /hotels/:id
  public updateHotel(hotel: Hotel): Observable<Hotel> {
      return this.http
        .put(API_URL + '/hotels/' + hotel.id, hotel)
        .map(response => {
          return new Hotel(response.json());
        })
        .catch(this.handleError);
    }

  // DELETE /hotels/:id
  public deleteHotelById(hotelId: number): Observable<null> {
      return this.http
        .delete(API_URL + '/hotels/' + hotelId)
        .map(response => null)
        .catch(this.handleError);
    }

}
