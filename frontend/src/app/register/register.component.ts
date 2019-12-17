import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SecurityService} from '../security.service';
import {RegisterUserDTO} from '../registeruserDTO';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  securityService: SecurityService;

  constructor(private http: HttpClient, securityService: SecurityService) {
    this.securityService = securityService;
  }

  ngOnInit() {
  }

  registerUserInDB(username1, password1, password2) {

    if (password1 === password2) {
      const registeredUser: RegisterUserDTO = {
        username: username1,
        password: password1
      };
      // alert(username + ' ' + password1 + ' ' + password2);
      this.securityService.registerUserInDB(registeredUser);
    }

  }
}
