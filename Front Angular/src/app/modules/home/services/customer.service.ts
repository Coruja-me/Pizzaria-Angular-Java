import { EventEmitter, Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private authorized: boolean = false;
  public eventEmitter = new EventEmitter<string>();
  private url: string = 'http://localhost:8080/api/spring/';

  constructor(private http: HttpClient) {}

  private get httpOptions() {
    const token = localStorage.getItem('Token') || '';
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': "" + token,
      }),
    };
  }

  public getAuthorized(): boolean {
    return this.authorized;
  }

  public getCustomers(): Observable<Array<Customer>> {
    this.authorized = true;
    return this.http.get<Array<Customer>>(`${this.url}customers`, this.httpOptions);
  }

  public postCustomer(customer: Customer): Observable<Customer> {
    this.eventEmitter.emit('Um registro de Customer foi adicionado!');
    return this.http.post<Customer>(
      `${this.url}customers`,
      {
        id: {
          telephone: customer.id.telephone,
          cpf: customer.id.cpf,
        },
        name: customer.name,
        address: customer.address,
      },
      this.httpOptions
    );
  }

  public putCustomer(customer: Customer): Observable<Customer> {
    this.eventEmitter.emit('Um registro de Customer foi modificado!');
    return this.http.put<Customer>(
      `${this.url}customers/${customer.id.telephone}/${customer.id.cpf}`,
      {
        id: {
          telephone: customer.id.telephone,
          cpf: customer.id.cpf,
        },
        name: customer.name,
        address: customer.address,
      },
      this.httpOptions
    );
  }

  public deleteCustomer(telephone: string, cpf: string): Observable<void> {
    this.eventEmitter.emit('Um registro de Customer foi removido!');
    return this.http.delete<void>(
      `${this.url}customers/${telephone}/${cpf}`,
      this.httpOptions
    );
  }
}
