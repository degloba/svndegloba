import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

import { Http, Response } from '@angular/http';
import { Todo } from '../todo';
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
  
  // API: GET /todos
  public getAllTodos(): Observable<Todo[]> {
      return this.http
        .get(API_URL)
        .map(response => {
          const todos = response.json();
          return todos.items.map((todo) => new Todo(todo));
        })
        .catch(this.handleError);
    }

  // API: POST /todos
  public createTodo(todo: Todo): Observable<Todo> {
      return this.http
        .post(API_URL + '/todos', todo)
        .map(response => {
          return new Todo(response.json());
        })
        .catch(this.handleError);
    }

  // API: GET /todos/:id
  public getTodoById(todoId: number): Observable<Todo> {
      return this.http
        .get(API_URL + '/todos/' + todoId)
        .map(response => {
          return new Todo(response.json());
        })
        .catch(this.handleError);
    }

  // API: PUT /todos/:id
  public updateTodo(todo: Todo): Observable<Todo> {
      return this.http
        .put(API_URL + '/todos/' + todo.id, todo)
        .map(response => {
          return new Todo(response.json());
        })
        .catch(this.handleError);
    }

  // DELETE /todos/:id
  public deleteTodoById(todoId: number): Observable<null> {
      return this.http
        .delete(API_URL + '/todos/' + todoId)
        .map(response => null)
        .catch(this.handleError);
    }

}
