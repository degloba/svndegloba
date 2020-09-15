import { Injectable } from '@angular/core';


import { environment } from 'environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Enviament } from '../model/enviament';

import { Observable } from 'rxjs/Observable';

//we can now access environment.apiUrl
const API_URL = environment.apiUrl;


@Injectable({
  providedIn: 'root'
})
export class EnviamentsService {

 constructor(private http: HttpClient) { }
  
  private handleError (error: HttpErrorResponse) {
      console.error('ApiService::handleError', error);
      return Observable.throw(error);
    }
  
  
  /**
   * 
   * {@link getAllHotels}</param>
   * [Todo]{@link Todo}
   * {@link Todo|TodoClass}
   * Anchors are supported : [Todo]{@link Todo#myproperty}
//for an external link
   *
   * @param {string} target  The target to process see {@link Todo}
   * @returns The processed target number
   */
  public getAllEnviaments(): Observable<Enviament[]> {
	  return this.http
        .get<Enviament[]>(API_URL + '/enviaments/', {responseType: 'json'})
        .map(response => {
          const enviaments = response;
          return enviaments.map((enviament) => new Enviament(enviament));
        })
        .catch(this.handleError);

    }

  /**
   * Example of usage:
   * <example-url>http://localhost/demo/mysample.component.html</example-url>
   * <example-url>/demo/mysample.component.html</example-url>
   */
    public createEnviament(enviament: Enviament): Observable<Enviament> {
      return this.http
        .post(API_URL + '/enviaments', enviament)
        .map(response => {
          return new Enviament(response);
        })
        .catch(this.handleError);
    }

  // API: GET /enviaments/:id
  public getEnviamentById(enviamentId: number): Observable<Enviament> {
      return this.http
        .get(API_URL + '/enviaments/' + enviamentId)
        .map(response => {
          return new Enviament(response);
        })
        .catch(this.handleError);
    }

  // API: PUT /enviaments/:id
  public updateEnviament(enviament: Enviament): Observable<Enviament> {
      return this.http
        .put(API_URL + '/enviaments/' + enviament.enviamentId, enviament)
        .map(response => {
          return new Enviament(response);
        })
        .catch(this.handleError);
    }

  // DELETE /enviaments/:id
  public deleteEnviamentById(enviamentId: number): Observable<null> {
      return this.http
        .delete(API_URL + '/enviaments/' + enviamentId)
        .map(response => null)
        .catch(this.handleError);
    }
}
