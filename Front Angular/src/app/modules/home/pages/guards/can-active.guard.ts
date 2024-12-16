import { CanActivateFn } from '@angular/router';

export const canActiveGuard: CanActivateFn = (route, state) => {
  if(route.params['usr'] === 'caua' && route.params['pswd']=== '0000'){ return true}
  return false;
};
