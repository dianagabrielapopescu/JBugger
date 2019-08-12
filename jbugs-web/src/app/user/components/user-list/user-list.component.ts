import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../../models/user.model';
import {Color} from '../../../app.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  @Input()
  public userList: User[];
  public showListOfColors = true;
  @Input()
  public colorList: Color[];

  constructor() { }

  alertUserFromList(user: User){
    alert(user.firstname);
  }
  ngOnInit() {
  }

}
