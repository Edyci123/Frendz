import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  @Input() id!: number;

  userDetails!: {username: string, description: string}; 

  constructor(private route: Router) {
  }

  ngOnInit(): void {
    this.userDetails = {username: "edyci123", description: "jkdjbkdsgsdf"};
  }

  onSeeDetails(id: number) {
    this.route.navigate(['/contacts/' + id]);
  }

}
