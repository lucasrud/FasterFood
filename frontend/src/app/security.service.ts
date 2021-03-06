import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './user';
import {RegisterUserDTO} from './registeruserDTO';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private sessionUser = new BehaviorSubject<User|null>(null);
  private regUser = 0;

  constructor(private http: HttpClient, private router: Router) {
    this.http.get<User>('/api/sessionUser').subscribe(
      u => this.sessionUser.next(u));

  }

  public getSessionUser(): Observable<User|null> {
    return this.sessionUser;
  }

  public login(username: string, password: string) {
    this.http.get<User>('/api/sessionUser', {
      headers: {
        authorization: 'Basic ' + btoa(username + ':' + password)
      }
    }).subscribe(
      u => this.sessionUser.next(u),
      () => this.sessionUser.next(null),
    );
    this.router.navigate(['/login']);
  }


  public registerUserInDB(registerUserDTO: RegisterUserDTO) {
    this.http.post<number>('/api/register/user', registerUserDTO).subscribe(u => {
      this.regUser = u;
    });
    return this.regUser;
  }

  public logout() {
    this.http.post('/api/logout', null).subscribe(
      () => this.sessionUser.next(null),
    );
  }
}
