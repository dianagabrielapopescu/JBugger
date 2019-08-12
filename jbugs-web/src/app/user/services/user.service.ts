import {Injectable} from '@angular/core';
import {User, UserRole} from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { } // private backendService: BackendService
  userHasCloseBugRole(userRoles: UserRole[]): boolean {
    for (const role of userRoles) {
      if (role == UserRole.CLOSE_BUG) {
        return true;
      }
    }
    return false;
  }
}
