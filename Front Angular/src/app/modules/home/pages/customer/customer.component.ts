import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.scss'
})
export class CustomerComponent implements OnInit{
  public customers: Array<Customer> = [];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.eventEmitter.subscribe(
      res => alert(`${res}`)
    )
    this.getCustomers();
  }
  private getCustomers(){
    this.customerService.getCustomers().subscribe({
      next: (res) => this.customers = res,
      error: (err) => console.log(err)
    });
  }

  public addCustomer(telephone: string, cpf: string, name: string, address: string){
    var customer: Customer = {
      id: {
        telephone: telephone,
        cpf: cpf
      },
      name: name,
      address: address
    };
      this.postCustomer(customer)
  }
  public modifyCustomer(telephone: string, cpf: string, name: string, address: string){
    var customer: Customer = {
      id: {
        telephone: telephone,
        cpf: cpf
      },
      name: name,
      address: address
    };
      this.putCustomer(customer)
  }


  public postCustomer(customer: Customer){
    return this.customerService.postCustomer(customer).subscribe({
      next: () => this.getCustomers(),
      error: (err) => console.log(err)
    })
  }

  public putCustomer(customer: Customer){
    return this.customerService.putCustomer(customer).subscribe({
      next: () => this.getCustomers(),
      error: (err) => console.log(err)
    })
  }
  public deleteCustomer(telephone: string, cpf: string){
    return this.customerService.deleteCustomer(telephone, cpf).subscribe({
      next: () => {
        this.customers = this.customers.filter(
          item => {
            return telephone !== item.id.telephone && cpf !== item.id.cpf
          }
        )
      },
      error: (err) => console.log(err)
    })
  }

  public exit(): boolean {
    if(confirm('Deseja sair da página?')){
      return true
    }
    return false
  }
}

