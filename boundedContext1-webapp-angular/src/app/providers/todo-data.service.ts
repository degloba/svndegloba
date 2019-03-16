import { Injectable } from '@angular/core';
import {Todo} from '../todo';

import { ApiService } from './api.service';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  
//Placeholder for last id so we can simulate
  // automatic incrementing of ids
  lastId: number = 0;

  // Placeholder for todos
  todos: Todo[] = [];

  constructor(private api: ApiService) {
  }

//Simulate POST /todos
  addTodo(todo: Todo): Observable<Todo> {
    return this.api.createTodo(todo);
  }

  // Simulate DELETE /todos/:id
  deleteTodoById(todoId: number): Observable<Todo> {
    return this.api.deleteTodoById(todoId);
  }

  // Simulate PUT /todos/:id
  updateTodo(todo: Todo): Observable<Todo> {
    return this.api.updateTodo(todo);
  }

  // Simulate GET /todos
  getAllTodos(): Observable<Todo[]> {
    return this.api.getAllTodos();
  }

  // Simulate GET /todos/:id
  getTodoById(todoId: number): Observable<Todo> {
    return this.api.getTodoById(todoId);
  }

  // Toggle complete
  toggleTodoComplete(todo: Todo) {
    todo.complete = !todo.complete;
    return this.api.updateTodo(todo);
  }
}
