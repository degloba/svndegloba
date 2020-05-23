import { Component, Input } from '@angular/core';
import { Hotel } from '../../model/hotel';

@Component({
  selector: 'app-hotels-list-footer',
  templateUrl: './hotels-list-footer.component.html',
  styleUrls: ['./hotels-list-footer.component.css']
})
export class HotelsListFooterComponent {

    @Input()
    hotels: Hotel[];

    constructor() {
    }

}