import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form!: FormGroup;
  error: string = '';
  loading: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onSubmit() {
    this.loading = true;
    this.authService.register(this.form.value).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: (error) => {
        this.error = 'An error has occured!';
        this.loading = false;
        console.log(error);
      }
    });
  }

  onHavingAnAccount() {
    this.router.navigate(['/login']);
  }
}
