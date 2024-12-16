import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pizza',
  templateUrl: './pizza.component.html',
  styleUrl: './pizza.component.scss'
})
export class PizzaComponent implements OnInit{
  constructor(private customerService: CustomerService, private activatedRouted: ActivatedRoute) {}

  ngOnInit(){
    this.customerService.eventEmitter.subscribe(
      res => alert(`${res}`)
    )

    this.activatedRouted.params.subscribe(
      res => console.log("Id: " + res['id'], "User: " + res['user'], res)
    )
    this.activatedRouted.queryParams.subscribe(
      res => console.log("Nome: " + res['nome'], res)
    )
  }
}
