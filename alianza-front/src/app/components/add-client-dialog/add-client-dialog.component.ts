import { Component } from '@angular/core';
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { ClientFormBaseComponent } from '../client-form-base/client-form-base.component';
import { ClientForm } from '../../interfaces';
import { FormGroup } from '@angular/forms';
import { ClientService } from '../../services/client/client.service';

@Component({
  selector: 'app-add-client-dialog',
  standalone: true,
  imports: [
    ClientFormBaseComponent,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
  ],
  templateUrl: './add-client-dialog.component.html',
  styleUrl: './add-client-dialog.component.scss',
})
export class AddClientDialogComponent {
  constructor(
    private matDialogRef: MatDialogRef<AddClientDialogComponent>,
    private clientService: ClientService
  ) {}

  submit(ev: FormGroup<ClientForm>) {
    if (ev.invalid) {
      return alert('Invalid form.');
    }

    this.clientService.createClient(ev.value).subscribe({
      next: (res) => {
        this.matDialogRef.close(res);
      },
      error: () => {
        alert('An error has occurred, please try again.');
      }
    });
  }
}
