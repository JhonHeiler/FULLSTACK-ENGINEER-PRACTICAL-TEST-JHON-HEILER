import { FormControl } from '@angular/forms';

export interface ClientForm {
  name: FormControl<string | null>;
  email: FormControl<string | null>;
  phone: FormControl<string | null>;
  startDate: FormControl<string | null>;
  endDate: FormControl<string | null>;
}

export interface AdvancedFilter {
  name: string | null;
  email: string | null;
  phone: string | null;
  startDate: string | null;
  endDate: string | null;
}

export interface SearchFilter {
  search?: string;
  advanced: Partial<AdvancedFilter>;
}