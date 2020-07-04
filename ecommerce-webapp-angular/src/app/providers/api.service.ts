import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Hotel } from '../model/hotel';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

//we can now access environment.apiUrl
const API_URL = environment.apiUrl;

/**
 * Servei on defineix totes les operacions que podem fer (GET/POST) contra el Backend i utilitzant 
 * @see {@link HttpClient}.
 * 
 */
@Injectable()
export class ApiService {
    
   /** 
    * Definicio d'una Property
    */  
    propietat: string;

  constructor(private http: HttpClient) { }
  
  private handleError (error: HttpErrorResponse) {
      console.error('ApiService::handleError', error);
      return Observable.throw(error);
    }
  

  
  /**
   * @example
   * This is a good example
   * 
   * Hola
   * 
   * {@link getAllHotels}</param>
   * [Todo]{@link Todo}
   * {@link Todo|TodoClass}
   * Anchors are supported : [Todo]{@link Todo#myproperty}
//for an external link
    * [Google]{@link http://www.google.com}
    * {@link http://www.apple.com|Apple
    * {@link https://github.com GitHub}
   *
   * @param {string} target  The target to process see {@link Todo}
   * @returns The processed target number
   */
  public getAllHotels(): Observable<Hotel[]> {
      return this.http
        .get<Hotel[]>(API_URL, {responseType: 'json'})
        .map(response => {
          const hotels = response;
          return hotels.map((hotel) => new Hotel(hotel));
        })
        .catch(this.handleError);
    }

  /**
   * Example of usage:
   * <example-url>http://localhost/demo/mysample.component.html</example-url>
   * <example-url>/demo/mysample.component.html</example-url>
   */
    public createHotel(hotel: Hotel): Observable<Hotel> {
      return this.http
        .post(API_URL + '/hotels', hotel)
        .map(response => {
          return new Hotel(response);
        })
        .catch(this.handleError);
    }

  // API: GET /hotels/:id
  public getHotelById(hotelId: number): Observable<Hotel> {
      return this.http
        .get(API_URL + '/hotels/' + hotelId)
        .map(response => {
          return new Hotel(response);
        })
        .catch(this.handleError);
    }

  // API: PUT /hotels/:id
  public updateHotel(hotel: Hotel): Observable<Hotel> {
      return this.http
        .put(API_URL + '/hotels/' + hotel.id, hotel)
        .map(response => {
          return new Hotel(response);
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
