import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user/components/user-list/user-list.component';
import { UserComponent } from './user/components/user/user.component';
import {UserModule} from "./user/user.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    UserModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
