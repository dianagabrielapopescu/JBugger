import {Component} from '@angular/core';
import {User, UserRole} from './models/user.model';

export enum Color { Red= 'red', Blue= 'blue', Yellow= 'yellow'}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'jbugs-web';
  subtitle = 'pleased to meet you';
  public colors: Color[] = [
    Color.Blue, Color.Red, Color.Yellow, Color.Red
  ];
  public users: User[] = [
    {
      firstname: 'diana',
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
  ];

}
