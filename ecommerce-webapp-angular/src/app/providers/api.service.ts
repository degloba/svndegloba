import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Hotel } from '../model/hotel';

import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';



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


}
