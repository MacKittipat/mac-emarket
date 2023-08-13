import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CateogryListComponent } from './pages/cateogry-list/cateogry-list.component';
import { CateogryInfoComponent } from './pages/cateogry-info/cateogry-info.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'category', component: CateogryListComponent},
  {path: 'category/info/:id', component: CateogryInfoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
