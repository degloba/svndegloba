import { Component, OnInit, NgZone } from '@angular/core';
import { AuthService } from '../shared/services/auth.service';
import { Router } from '@angular/router';

//import {TodoDataService} from '../../providers/todo-data.service';
import { Todo } from '../model/todo';



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  //providers: [TodoDataService]
})
export class DashboardComponent implements OnInit {

    todos: Todo[] = [];

  constructor(
    public authService: AuthService,
    //private todoDataService: TodoDataService,
    public router: Router,
    public ngZone: NgZone
  ) { }

  public ngOnInit() {
      //this.todoDataService
      //  .getAllTodos()
      //  .subscribe(
      //    (todos) => {
      //      this.todos = todos;
      //    }
      //  );
    }
  
  onAddTodo(todo) {
      //this.todoDataService
      //  .addTodo(todo)
      //  .subscribe(
      //   (newTodo) => {
      //      this.todos = this.todos.concat(newTodo);
      //    }
      //  );
    }

  onToggleTodoComplete(todo) {
      // this.todoDataService
      //  .toggleTodoComplete(todo)
      //  .subscribe(
      //    (updatedTodo) => {
      //      todo = updatedTodo;
      //   }
      //  );
    }

  onRemoveTodo(todo) {
      //this.todoDataService
      //  .deleteTodoById(todo.id)
      //  .subscribe(
      //    (_) => {
      //     this.todos = this.todos.filter((t) => t.id !== todo.id);
      //   }
      // );
    }

}

