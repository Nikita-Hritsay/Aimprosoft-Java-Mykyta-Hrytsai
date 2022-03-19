import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from "./modules/error/error.component";

const routes: Routes = [
  {
    path: 'web/departments',
    loadChildren: () => import('./modules/department/department.module').then(m => m.DepartmentModule)
  },
  {path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}