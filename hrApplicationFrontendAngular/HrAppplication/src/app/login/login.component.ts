import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  constructor(private authService: AuthService, private router: Router,private route: ActivatedRoute) { }
  loginForm: FormGroup
  ngOnInit(): void {

    this.loginForm = new FormGroup({
      username: new FormControl(this.username, Validators.required),
      password: new FormControl(this.password, Validators.required),
    });
  }
  error = null;
  username = '';
  password = '';
  login() {
    this.authService.login(this.loginForm.value).subscribe(
      response => {
        console.log(response)
        let userRole = this.authService.loginSuccess(response);
        if(userRole==="ROLE_ADMIN"){
          this.router.navigate(['../admin'], {relativeTo: this.route});
        }else{
          //hence user is employee
          this.router.navigate(['../employee'], {relativeTo: this.route});
        }
      },
      error => {
        this.error = error.message;
        console.log(error);
      }
    );
  }
}

