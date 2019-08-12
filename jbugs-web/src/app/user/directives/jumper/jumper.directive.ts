import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appJumper]'
})
export class JumperDirective {

  constructor(private el: ElementRef) { }

  @HostListener('mouseenter') onMouseEnter() {
    if (this.el.nativeElement.style.cssFloat == 'left') {
      this.el.nativeElement.style.cssFloat = 'right';
    } else {
      this.el.nativeElement.style.cssFloat = 'left';
    }
  }

  @HostListener('mouseleave') onMouseLeave() {
  }
}
