import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserComponent} from './components/user/user.component';
import {JumperDirective} from './directives/jumper/jumper.directive';
import {AuthorizationDirective} from './directives/authorization/authorization.directive';
import {BackendModule} from '../core/backend/backend.module';


@NgModule({
  declarations: [
    UserListComponent,
    UserComponent,
    JumperDirective,
    AuthorizationDirective
  ],
  imports: [
    CommonModule,
    BackendModule
  ],
  exports: [UserListComponent,
    JumperDirective]
})
export class UserModule { }
