import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PedidoComponent } from './pages/home/home.component';
import { PedidoRoutingModule } from './pedido.routes';



@NgModule({
  declarations: [PedidoComponent],
  imports: [
    CommonModule,
    PedidoRoutingModule
  ],
})
export class PedidoModule { }
