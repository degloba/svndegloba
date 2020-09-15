import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';


@Injectable()
export class EventService {

    source = new Subject<boolean>();
    save = new Subject<boolean>();

    displayStream = this.source.asObservable();
    saveStream = this.save.asObservable();

    displayCancel(estado: boolean) {
        this.source.next(estado);
    }
    displaySave(estado: boolean) {
        this.save.next(estado);
    }

}
