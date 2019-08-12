import {Injectable} from '@angular/core';
import {TestUser, User, UserRole} from '../models/user.model';
import {Observable, of} from 'rxjs';
import {BackendService} from '../../core/backend/services/backend.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private backendService: BackendService) { }
  userHasCloseBugRole(userRoles: UserRole[]): boolean {
    for (const role of userRoles) {
      if (role == UserRole.CLOSE_BUG) {
        return true;
      }
    }
    return false;
  }
  getAllUsers(): Observable<TestUser[]> {
    return this.backendService.get('http://localhost:8080/myapp/users');
   /*
    return of( [
      {
        firstname: 'Diana',
        lastname: 'pop',
        age: 20,
        roles: [UserRole.CREATE_BUG, UserRole.CLOSE_BUG]
      },
      {
        firstname: 'perry',
        lastname: 'cox',
        age: 20,
        roles: [UserRole.EDIT_BUG]
      },
      {
        firstname: 'abra',
        lastname: 'cadrabra',
        age: 100,
        roles: [UserRole.EDIT_BUG]
      },
      {
        firstname: 'oh',
        lastname: 'yeah',
        age: 2,
        roles: [UserRole.CLOSE_BUG]
      }
    ]).pipe(delay(5000));
  */
  }
}
