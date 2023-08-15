import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CateogryListComponent } from './pages/cateogry-list/cateogry-list.component';
import { CateogryInfoComponent } from './pages/cateogry-info/cateogry-info.component';
import { CateogryCreateComponent } from './pages/cateogry-create/cateogry-create.component';
import { CateogryUpdateComponent } from './pages/cateogry-update/cateogry-update.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'category', component: CateogryListComponent },
  { path: 'category/info/:id', component: CateogryInfoComponent },
  { path: 'category/create', component: CateogryCreateComponent },
  { path: 'category/update/:id', component: CateogryUpdateComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
