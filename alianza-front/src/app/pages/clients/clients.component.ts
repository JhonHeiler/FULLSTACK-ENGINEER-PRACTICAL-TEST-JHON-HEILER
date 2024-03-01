import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';

import { SearchBarComponent } from '../../components/search-bar/search-bar.component';
import { SidenavComponent } from '../../components/sidenav/sidenav.component';
import { AddClientDialogComponent } from '../../components/add-client-dialog/add-client-dialog.component';
import { AdvancedFilter, SearchFilter } from '../../interfaces';
import { ReportService } from '../../services/report/report.service';
import { ClientDTO } from '../../dto/client.dto';
import { ClientService } from '../../services/client/client.service';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatTableModule,
    SearchBarComponent,
    SidenavComponent,
  ],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.scss',
})
export class ClientsComponent implements OnInit {
  dataSource: ClientDTO[] = [];
  tableSchema: { key: keyof ClientDTO; label: string }[] = [
    { key: 'sharedKey', label: 'Shared Key' },
    { key: 'name', label: 'Business ID' },
    { key: 'email', label: 'E-mail' },
    { key: 'phone', label: 'Phone' },
    { key: 'startDate', label: 'Data Added' },
  ];
  displayedColumns = [...this.tableSchema.map((e) => e.key), 'actions'];

  constructor(
    private clientService: ClientService,
    private matDialog: MatDialog,
    private reportService: ReportService
  ) {}

  ngOnInit() {
    this.loadClients();
  }

  downloadReport() {
    this.reportService.downloadClientReport(this.dataSource);
  }

  openDialog() {
    this.matDialog
      .open(AddClientDialogComponent)
      .beforeClosed()
      .subscribe((res) => {
        if (res) this.loadClients();
      });
  }

  loadClients() {
    this.clientService.getClientsPage(0, 10).subscribe((clients) => {
      this.dataSource = clients;
    });
  }

  search(ev: SearchFilter) {
    const petition = ev.search
      ? this.clientService.searchClientBySharedKey(ev.search)
      : this.clientService.searchClients(this.searchMapper(ev.advanced));

    petition.subscribe((res) => {
      this.dataSource = res;
    });
  }

  private searchMapper(dto: Partial<AdvancedFilter>) {
    Object.entries(dto as any).forEach(([key, value]) => {
      console.log({ value, key });
      
      if (!value && typeof key === 'string') delete dto[key as keyof Partial<AdvancedFilter>];
      if (dto.endDate) {
        dto.endDate = this.formatDate(dto.endDate);
      }
      if (dto.startDate) {
        dto.startDate = this.formatDate(dto.startDate);
      }
    })
    return dto;
  }

  private formatDate(date: string) {
    return new Date(date).toISOString().split('T')[0];
  }
}