import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HotelDataService } from '../services/hotel-data.service';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements OnInit {

    hotels: any = ["hotel #1", "hotel #2", "hotel #3", "hotel #4", "hotel #5"]
    @Input() startDate: any
    @Input() endDate: any
    @Input() date: Array<Date> = []
    @Output() hideHotels = new EventEmitter()

    constructor(private hotelData: HotelDataService) { 
    }

    ngOnInit(): void {
        // Use start and end date to search for hotels
    }

    selectHotel(info: any) {
        this.hotelData = info
        this.hideHotels.emit()
    }
}