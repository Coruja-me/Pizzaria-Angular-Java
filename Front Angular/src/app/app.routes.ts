import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './modules/home/pages/customer/customer.component';
import { PizzaComponent } from './modules/home/pages/pizza/pizza.component';
import { NotFoundComponent } from './modules/home/pages/not-found/not-found.component';
import { HomeComponent } from './modules/home/pages/home/home.component';
import { canActiveGuard } from './modules/home/pages/guards/can-active.guard';
import { canDeactiveGuard } from './modules/home/pages/guards/can-deactive.guard';
import { canMatchGuard } from './modules/home/pages/guards/can-match.guard';

export const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full' },
  {path: 'customer/:usr/:pswd', component: CustomerComponent, pathMatch: 'full', canActivate: [canActiveGuard], canDeactivate: [canDeactiveGuard]},
  {path: 'pizza', component: PizzaComponent, children: [
    {path: ':id/:user', component: PizzaComponent},
  ]},
  {path: 'pedido', loadChildren: () => import ('./pedido/pedido.module').then(x => x.PedidoModule), canMatch: [canMatchGuard]},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '404'},
];
