import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Hotel } from '../../model/hotel';

@Component({
  selector: 'app-hotels-list-item',
  templateUrl: './hotels-list-item.component.html',
  styleUrls: ['./hotels-list-item.component.css']
})
export class HotelsListItemComponent {

  @Input() hotel: Hotel;

  @Output()
  remove: EventEmitter<Hotel> = new EventEmitter();

  @Output()
  toggleComplete: EventEmitter<Hotel> = new EventEmitter();

  constructor() {
  }

  toggleHotelComplete(hotel: Hotel) {
    this.toggleComplete.emit(hotel);
  }

  removeHotel(hotel: Hotel) {
    this.remove.emit(hotel);
  }

}




