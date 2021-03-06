import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../models/user.model";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @Input()
  userSentFromUserListComponent: User;
  @Output()
  public outputField = new EventEmitter<User>();
  constructor() { }

  alertUser(person: User){
    this.outputField.emit(person);
  }
  ngOnInit() {
  }

}
