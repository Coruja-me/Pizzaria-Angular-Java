import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerComponent } from './pages/customer/customer.component';
import { FormsModule } from '@angular/forms';
import { PizzaComponent } from './pages/pizza/pizza.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { HomeComponent } from './pages/home/home.component';



@NgModule({
  declarations: [CustomerComponent, PizzaComponent, NotFoundComponent, HomeComponent],
  imports: [
    CommonModule
  ],
  exports: [CustomerComponent, PizzaComponent, NotFoundComponent, HomeComponent],
})
export class HomeModule { }
