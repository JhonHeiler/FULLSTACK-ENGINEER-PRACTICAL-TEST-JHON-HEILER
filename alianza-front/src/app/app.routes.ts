import { Routes } from '@angular/router';
import { ClientsComponent } from './pages/clients/clients.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

export const routes: Routes = [
  { path: '', redirectTo: 'clients', pathMatch: 'full' },
  { path: 'clients', component: ClientsComponent },
  { path: '**', component: NotFoundComponent },
];
