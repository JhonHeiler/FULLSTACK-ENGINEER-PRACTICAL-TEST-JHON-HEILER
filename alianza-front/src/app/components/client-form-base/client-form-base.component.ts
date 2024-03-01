import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ClientForm } from '../../interfaces';
import { provideNativeDateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-client-form-base',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [
    CommonModule,
    MatButtonModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
  ],
  templateUrl: './client-form-base.component.html',
  styleUrl: './client-form-base.component.scss',
})
export class ClientFormBaseComponent implements OnInit {
  @Input() formTitle = '';
  @Input() required = true;
  @Output() getClientForm = new EventEmitter<FormGroup<ClientForm>>();

  clientForm!: FormGroup<ClientForm>;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.clientForm = this.fb.group({
      name: ['', this.required ? Validators.required : null],
      email: ['', this.required ? Validators.required : null],
      phone: ['', this.required ? Validators.required : null],
      startDate: ['', this.required ? Validators.required : null],
      endDate: ['', this.required ? Validators.required : null],
    });
  }

  getForm(): void {
    this.getClientForm.emit(this.clientForm);
  }
}
