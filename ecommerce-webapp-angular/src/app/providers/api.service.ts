import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';


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
