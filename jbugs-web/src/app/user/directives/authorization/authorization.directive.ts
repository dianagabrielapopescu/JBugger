import {AfterViewInit, Directive, ElementRef, Input, Renderer2} from '@angular/core';
import {UserRole} from '../../models/user.model';
import {UserService} from '../../services/user.service';

@Directive({
  selector: '[appAuthorization]'
})
export class AuthorizationDirective implements AfterViewInit {
  @Input()
  userRoles: UserRole[];
  constructor(private el: ElementRef, private userService: UserService) { }
  ngAfterViewInit() {
    (this.el.nativeElement as HTMLButtonElement).disabled = true;
    /*
    for (const role of this.userRoles) {
      if (role == UserRole.CLOSE_BUG) {
        (this.el.nativeElement as HTMLButtonElement).disabled = false;
      }
    }
     */
    if (this.userService.userHasCloseBugRole(this.userRoles)) {
      (this.el.nativeElement as HTMLButtonElement).disabled = false;
    }
  }
}
