import { EventEmitter, Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private authorized: boolean = false
  public eventEmitter = new EventEmitter();
  private url: string = "http://localhost:8080/api/spring/"

  constructor(private http : HttpClient) {  }

  public getAuthorized(): boolean {
    return this.authorized
  }

  public getCustomers(): Observable<Array<Customer>>{
    this.authorized = true;
    return this.http.get<Array<Customer>>(`${this.url}customers`).pipe(
      res => res, error => error
    )
  }
  public postCustomer(customer: Customer): Observable <Customer>{
    this.eventEmitter.emit("Um registro de Customer foi adicionado!")
    return this.http.post<Customer>(`${this.url}customers`, {
      id: {
        telephone: customer.id.telephone,
        cpf: customer.id.cpf
      },
      name: customer.name,
      address: customer.address
    }, {
      headers: {
        "Content-Type": "application/json"
      }
    }).pipe(
      res => res, error => error
    );
  }
  public putCustomer(customer: Customer): Observable<Customer>{
    this.eventEmitter.emit("Um registro de Customer foi modificado!")
    return this.http.put<Customer>(`${this.url}customers/${customer.id.telephone}/${customer.id.cpf}`, {
      id: {
        telephone: customer.id.telephone,
        cpf: customer.id.cpf
      },
      name: customer.name,
      address: customer.address
    }).pipe(
      res => res, error => error
    )
  }
  public deleteCustomer(telephone: string, cpf: string){
    this.eventEmitter.emit("Um registro de Customer foi removido!")
    return this.http.delete(`${this.url}customers/${telephone}/${cpf}`).pipe(
      res => res, error => error
    )
  }
}
