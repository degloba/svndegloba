import { Component, OnInit } from '@angular/core';

import { Observable } from "rxjs";

import { AllModules, ColumnApi, GridApi, Module } from '@ag-grid-enterprise/all-modules';
import "@ag-grid-enterprise/all-modules/dist/styles/ag-grid.css";
import "@ag-grid-enterprise/all-modules/dist/styles/ag-theme-alpine.css";

import {cloneDeep} from "lodash";

@Component({
  selector: 'ricdh-grid',
  template: 
		`<ag-grid-angular
			#agGrid 
			style="width: 1000px; height: 500px;"
			id="myGrid" 
			class="ag-theme-alpine" [modules]="modules"
			[columnDefs]="columnDefs" 
			[defaultColDef]="defaultColDef"
			[enableRangeSelection]="true" 
			[getRowNodeId]="getRowNodeId"
			[rowData]="rowData" 
			(gridReady)="onGridReady($event)">
		</ag-grid-angular>`,
})
export class HotelsComponent {
	private gridApi: any;
	private gridColumnApi: any;
	
	public modules: Module[] = AllModules;
	public columnDefs: any;
	public defaultColDef: any;
	public getRowNodeId: any;
	public rowData: any[];

  constructor() { 
	
	
	this.columnDefs = [
		{
			field: 'code',
			maxwidth: 90,
		},
		{
			field: 'name',
			maxwidth: 200,
		},
		{
			field: 'bid',
			cellClass: 'cell-number',
			valueFormatter: numberFormatter,
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
		{
			field: 'mid',
			cellClass: 'cell-number',
			valueFormatter: numberFormatter,
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
		{
			field: 'ask',
			cellClass: 'cell-number',
			valueFormatter: numberFormatter,
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
		
		{
			field: 'volume',
			cellClass: 'cell-number',			
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
	];
	this.defaultColDef = {
		flex: 1,
		minWidth: 100,
		resizable: true,
	};
	
	this.getRowNodeId = function(data: { code: any; }) {
		return data.code;
	};
}

onGridReady(params) {
	this.gridApi = params.api;
	this.gridColumnApi = params.columnApi;
	
	var mockServer = createMockServer(); 
	var	initialLoad$ = mockServer.initialLoad();
	var updates$ = mockServer.byRowUpdates();
	
		initialLoad$.subscribe(function(rowData) {
			params.api.setRowData(rowData);
			updates$.subscribe(function(updates) {
				
				return params.api.applyTransaction({ update: updates});
			});
		});
	}	
}
	
function numberFormatter(params) {
	if (typeof params.value === 'number') {
		return params.value.toFixed(2);
	}
	return params.value;
}

function createMockServer() {
	function MockServer() {
		'use strict';
		this.rowData = [];
	}
	MockServer.prototype.initialLoad = function() {
		return Observable.fromPromise(new Promise((resolve, reject) => {
			let httpRequest = new XMLHttpRequest();
			httpRequest.open('GET',
			'https://raw.githubusercontent.com/ag-grid/ag-grid/master/grid-packages/ag-grid-docs/src/stocks.json');
			httpRequest.send();
			httpRequest.onreadystatechange = () => {
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					
					let dataSet = JSON.parse(httpRequest.responseText);
					let reducedDataSet = dataSet.slice(0,200);
					this.rowData = this.backfillData(reducedDataSet);
					resolve(cloneDeep(this.rowData));
				}
			};
		})
		);
	};
	
	MockServer.prototype.byRowUpdates = function() {
		var that = Object(this);
		return Observable.create(function(observer) {
			var interval = window.setInterval(function() {
				var changes = [];
				that.makeSomePriceChanges(changes);
				that.makeSomeVolumeChanges(changes);
				observer.next(changes);
				}, 1000);
				return function() {
					window.clearInterval(interval);
				};
			});
		};
		
	MockServer.prototype.allDataUpdates = function() {
		var that = Object(this);
		return Observable.create(function(observer) {
			var interval = window.setInterval(function() {
				var changes = [];
				that.makeSomePriceChanges(changes);
				this.makeSomeVolumeChanges(changes);
				observer.next(cloneDeep(that.rowData));
				}, 1000);
				alert("all");
				return function() {
					window.clearInterval(interval);
				};
			});
		};
	
	MockServer.prototype.backfillData = function(rowData) {
		var that = Object(this);
		rowData.forEach(function(dataItem) {
			dataItem.volume = Math.floor(Math.random() * 10000 + 100);
			dataItem.mid = Math.random() * 300 + 20;
			that.setBidAndAsk(dataItem);
		});
		return rowData;
	};		
	
	
	MockServer.prototype.makeSomeVolumeChanges = function(changes) {
		for (var i=0; i < 10; i++) {
			var index = Math.floor(this.rowData.length * Math.random()),
				currentRowData = this.rowData[index],
				move = Math.floor(10 * Math.random()) - 5,
				newValue = currentRowData.volume + move;
			currentRowData.volume = newValue;
			changes.push(currentRowData);
			
		}
	};
	
	MockServer.prototype.makeSomePriceChanges = function(changes) {
		for (var i=0; i < 10; i++) {
			var index = Math.floor(this.rowData.length * Math.random()),
				currentRowData = this.rowData[index],
				move = Math.floor(30 * Math.random()) / 10 - 1,
				newValue = currentRowData.volume + move;
			currentRowData.mid = newValue;
			this.setBidAndAsk(currentRowData)
			changes.push(currentRowData);
	}
	};

	MockServer.prototype.setBidAndAsk = function(dataItem) {
		dataItem.bid = dataItem.mid * 0.98;
		dataItem.ask = dataItem.mid * 1.02;
	};

	return new MockServer();

}
