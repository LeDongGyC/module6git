
import { Routes, RouterModule } from '@angular/router';
import { InforAccountComponent } from './infor-account/infor-account.component';
import { AuthGuard } from '../../service/auth.guard';
import { UserCreateComponent } from './user-create/user-create.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: 'infor-account', component: InforAccountComponent, canActivate: [AuthGuard],
    data: {
      roles: ['admin', 'user']
    }
  },
  {
    path: 'create-user', component: UserCreateComponent,
    data: {
      roles: ['admin', 'user']
    }
  },
  {
    path: 'userList/edit-user/:id', component: UserEditComponent,
    data: {
      roles: ['admin']
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {
}
