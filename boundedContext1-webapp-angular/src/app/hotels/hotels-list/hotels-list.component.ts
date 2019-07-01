import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Hotel } from '../../model/hotel';

@Component({
  selector: 'app-hotels-list',
  templateUrl: './hotels-list.component.html',
  styleUrls: ['./hotels-list.component.css']
})
export class HotelsListComponent {

  @Input()
  hotels: Hotel[];

  @Output()
  remove: EventEmitter<Hotel> = new EventEmitter();

  @Output()
  toggleComplete: EventEmitter<Hotel> = new EventEmitter();

  constructor() {
  }

  onToggleHotelsComplete(hotels: Hotel) {
    this.toggleComplete.emit(hotels);
  }

  onRemoveHotels(hotels: Hotel) {
    this.remove.emit(hotels);
  }

}


