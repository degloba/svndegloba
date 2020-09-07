import { Observable, from as fromPromise} from "rxjs";


import { cloneDeep } from "lodash";

/* Classe MOCK pel AgGrid + RxJs */
export class MockServer {

	   rowData:any;
   		
		/* Observable de la carrega de tot el grid */
	  initialLoad(): Observable<any> {
		
 		var promesa  = new Promise((resolve, _reject) => {
			let httpRequest = new XMLHttpRequest();
			/*httpRequest.open('GET',
				'https://raw.githubusercontent.com/ag-grid/ag-grid/master/grid-packages/ag-grid-docs/src/stocks.json'); 
				*/
			
			httpRequest.open('GET',	'http://localhost:8083/enviaments/');     // 8083 = reactive
			httpRequest.send();

			httpRequest.onreadystatechange = () => {
				
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					let dataSet = JSON.parse(httpRequest.responseText);
	
					let reducedDataSet = dataSet.slice(0, 200);

					this.rowData = this.backfillData(reducedDataSet);
				
					resolve(cloneDeep(this.rowData));	
					
				}
			};  
		})
			
		// convertim un Promise a un Observable
		return fromPromise(promesa)
	}
	
	/* Observable de la carrega només de les files modificades */
	   byRowUpdates(): Observable<any> {
							
		return Observable.create(function(observer) {
				let interval = window.setInterval(function() {
				let changes = [];

				/////this.allDataUpdates();
				/////this.makeSomePriceChanges(changes);
				
				///////this.makeSomeVolumeChanges(changes);
				observer.next(changes);
			}, 1000);
			return function() {
				window.clearInterval(interval);
			};
		})
	}
	

	
	 backfillData(reducedDataSet: any): any {
			reducedDataSet.forEach((dataItem) => {
			////dataItem.volume = Math.floor(Math.random() * 10000 + 100);
			////dataItem.mid = Math.random() * 300 + 20;
			this.setBidAndAsk(dataItem);
		});
		
		return reducedDataSet;
	}

	 setBidAndAsk(dataItem: { id: string; comandaId: string; estat: string; }) {
		this.rowData = dataItem;
		////dataItem.id = id;   ////dataItem.mid * 0.98;
		/////dataItem.comandaId = dataItem.mid * 1.02;
	}

	/* simula modificacions */
/*	makeSomeVolumeChanges(changes: any[]): any {

		for (var i = 0; i < 10; i++) {
			var index = Math.floor(this.rowData.length * Math.random()),
				currentRowData = this.rowData[index],
				move = Math.floor(10 * Math.random()) - 5,
				newValue = currentRowData.volume + move;
			currentRowData.volume = newValue;
			changes.push(currentRowData);

		};
	}*/

	/* simula modificacions */
	/* makeSomePriceChanges(changes) {

		for (var i = 0; i < 10; i++) {
			
			var index = Math.floor(this.rowData.length * Math.random()),
				currentRowData = this.rowData[index],
				move = Math.floor(30 * Math.random()) / 10 - 1,
				newValue = currentRowData.volume + move;
			currentRowData.mid = newValue;
			this.setBidAndAsk(currentRowData)			
			changes.push(currentRowData);

		}

	}*/

	 allDataUpdates() {
		
		return Observable.create(function(observer) {
			var interval = window.setInterval(function() {
				var changes = [];
				////this.makeSomePriceChanges(changes);
				////this.makeSomeVolumeChanges(changes);
				observer.next(cloneDeep(this.rowData));
			}, 1000);

			return function() {
				window.clearInterval(interval);
			}
		})
	}
	
	constructor() {
		this.rowData = [];
	}

}
