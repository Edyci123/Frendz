import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-detailed-contact',
  templateUrl: './detailed-contact.component.html',
  styleUrls: ['./detailed-contact.component.css']
})
export class DetailedContactComponent implements OnInit {

  userDetails: any;
  
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    //call backend for userDetails
  }

}
