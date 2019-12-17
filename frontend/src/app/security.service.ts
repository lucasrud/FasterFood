import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './user';
import {RegisterUserDTO} from './registeruserDTO';


@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private sessionUser = new BehaviorSubject<User|null>(null);

  constructor(private http: HttpClient) {
    this.http.get<User>('/api/sessionUser').subscribe(
      u => this.sessionUser.next(u)
    );
  }

  public getSessionUser(): Observable<User|null> {
    return this.sessionUser;
  }

  public login(username: string, password: string) {
    this.http.get<User>('/api/sessionUser', {
      headers: {
        authorization : 'Basic ' + btoa(username + ':' + password)
      }
    }).subscribe(
      u => this.sessionUser.next(u),
      () => this.sessionUser.next(null),
    );
  }

  // Baustelle
  public registerUserInDB(registerUserDTO: RegisterUserDTO) {
    this.http.post('/api/register/user', registerUserDTO).subscribe(() => this.sessionUser.next(null));
  }

  public logout() {
    this.http.post('/api/logout', null).subscribe(
      () => this.sessionUser.next(null),
    );
  }
}
