import { AuthService } from '../../_services/auth.service';
import { TokenStorageService } from '../../_services/token-storage.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.css']
})
export class ToolBarComponent implements OnInit {

  constructor(private router: Router, private token: TokenStorageService,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  onHome() {
    this.router.navigate(['/']);
  }

  onContacts() {
    this.router.navigate(['/contacts'])
  }

  onLogout() {
    this.authService.logout();
  }

  isLoggedIn() {
    return (!this.token.getToken());
  }

}
