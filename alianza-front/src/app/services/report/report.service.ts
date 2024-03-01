import { Injectable } from '@angular/core';

import * as XLSX from 'xlsx';
import { ClientDTO } from '../../dto/client.dto';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  constructor() {}

  private clientReport(jsonData: ClientDTO[]): string {
    const worksheet = XLSX.utils.json_to_sheet(
      jsonData.map((e) => {
        const excelExport: { [key: string]: string } = {};
        excelExport['Shared Key'] = e.sharedKey;
        excelExport['Name'] = e.name;
        excelExport['Email'] = e.email;
        excelExport['Phone'] = e.phone;
        excelExport['Start date'] = e.startDate;
        excelExport['End date'] = e.endDate;
        return excelExport;
      })
    );

    worksheet['!cols'] = Array(6)
      .fill(20)
      .map((wch) => ({ wch }));
    return XLSX.utils.sheet_to_csv(worksheet);
  }

  downloadClientReport(jsonData: ClientDTO[]) {
    const csvData = this.clientReport(jsonData);
    const blob = new Blob([csvData], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const tempUrl = window.URL.createObjectURL(blob);
    link.href = tempUrl;
    link.setAttribute('download', 'client_report.csv');
    link.click();

    // remove
    window.URL.revokeObjectURL(tempUrl);
    link.remove();
  }
}
