import { CanMatchFn, Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { inject } from '@angular/core';

export const canMatchGuard: CanMatchFn = (route, segments) => {
  const router = inject(Router)
  const cService = inject(CustomerService)

  if(cService.getAuthorized()){
    return true
  }
  router.navigate(["/"])
  return false;
};
