import { RouterModule, Routes } from "@angular/router"
import { PedidoComponent } from "./pages/home/home.component"
import { NgModule } from "@angular/core"

const routes: Routes = [
  {path: '', component: PedidoComponent}
]
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PedidoRoutingModule {}
