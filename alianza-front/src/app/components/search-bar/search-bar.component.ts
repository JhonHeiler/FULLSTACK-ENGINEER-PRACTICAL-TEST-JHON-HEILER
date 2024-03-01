import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { ClientFormBaseComponent } from '../client-form-base/client-form-base.component';
import { ClientForm, SearchFilter } from '../../interfaces';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [ClientFormBaseComponent, FormsModule, MatButtonModule],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.scss',
})
export class SearchBarComponent {
  search = '';
  showAdvanced = false;
  @Output() getFilter = new EventEmitter<SearchFilter>();

  getSimpleSearchForm() {
    if (!this.search) {
      return alert('Shared key is required.');
    }
    this.getFilter.emit({ search: this.search, advanced: {} });
  }

  getSearchForm(ev: FormGroup<ClientForm>) {
    this.showAdvanced = false;
    this.getFilter.emit({ search: '', advanced: ev.value });
  }
}
