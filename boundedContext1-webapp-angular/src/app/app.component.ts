import { Component, OnInit } from '@angular/core';
import {TodoDataService} from './providers/todo-data.service';
import { Todo } from './model/todo';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['app.component.css'],
  ///////////providers: [TodoDataService]
})
export class AppComponent implements OnInit {
  title = 'Casino';
  
  todos: Todo[] = [];
  
  constructor() {
        }
  
  public ngOnInit() {
      
    }
  


}