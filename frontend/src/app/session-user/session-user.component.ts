import { Component, OnInit } from '@angular/core';
import {SecurityService} from '../security.service';
import {User} from '../user';


@Component({
  selector: 'app-session-user',
  templateUrl: './session-user.component.html',
  styleUrls: ['./session-user.component.css']
})
export class SessionUserComponent implements OnInit {
  private sessionUser: User|null;

  constructor(private securityService: SecurityService) { }

  ngOnInit() {
    this.securityService.getSessionUser().subscribe(
      u => this.sessionUser = u
    );
  }

}
