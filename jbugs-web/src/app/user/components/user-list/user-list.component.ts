import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TestUser, User} from '../../models/user.model';
import {Color} from '../../../app.component';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  /*@Input()*/
  public userList: TestUser[];
  public showListOfColors = true;
  @Input()
  public colorList: Color[];

  constructor(private userService: UserService) {
    userService.getAllUsers().subscribe( (returnedUserList) => {
      this.userList = returnedUserList;
    });
  }

  alertUserFromList(user: User) {
    alert(user.firstname);
  }
  ngOnInit() {
  }

}
