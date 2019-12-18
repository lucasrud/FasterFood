import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {SecurityService} from '../security.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  sessionUser: User|null = null;
  loginData = {
    username: '', password: ''
  };

  constructor(private securityService: SecurityService) { }

  ngOnInit() {
    this.securityService.getSessionUser().subscribe(
      u => this.sessionUser = u
    );
  }

  login() {
    this.securityService.login(this.loginData.username, this.loginData.password);
  }
}
