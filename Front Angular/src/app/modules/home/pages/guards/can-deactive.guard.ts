import { CanDeactivateFn } from '@angular/router';
import { CustomerComponent } from '../customer/customer.component';

export const canDeactiveGuard: CanDeactivateFn<CustomerComponent> = (component: CustomerComponent, currentRoute, currentState, nextState) => {
  return component.exit();
};
