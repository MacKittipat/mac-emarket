import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { CateogryListComponent } from './pages/cateogry-list/cateogry-list.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CateogryInfoComponent } from './pages/cateogry-info/cateogry-info.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { TreeTableModule } from 'primeng/treetable';
import { CateogryCreateComponent } from './pages/cateogry-create/cateogry-create.component';
import { CateogryUpdateComponent } from './pages/cateogry-update/cateogry-update.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CateogryListComponent,
    CateogryInfoComponent,
    CateogryCreateComponent,
    CateogryUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NoopAnimationsModule,
    TreeTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
