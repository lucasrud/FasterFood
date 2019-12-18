import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SecurityService} from '../security.service';
import {User} from '../User';
import {RegisterUserDTO} from '../registeruserDTO';
import {Router} from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  securityService: SecurityService;
  registeredUser: RegisterUserDTO;
  registeredUserNumber = 0;
  // sessionUser: User|null = null;
  sessionUser: User;

  constructor(private http: HttpClient, securityService: SecurityService, private router: Router) {
    this.securityService = securityService;
  }

  ngOnInit() {
  }

  registerUserInDB(username1, password1, password2) {
    this.registeredUserNumber = 0;
    if (password1 === password2) {
      this.registeredUser = {
        username: username1,
        password: password1
      };
      this.registeredUserNumber = this.securityService.registerUserInDB(this.registeredUser);
      this.router.navigate(['/login']);
    }

  }
}
