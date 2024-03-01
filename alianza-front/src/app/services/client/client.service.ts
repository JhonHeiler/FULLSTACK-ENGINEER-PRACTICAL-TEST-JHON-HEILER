import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ClientDTO } from '../../dto/client.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = 'http://localhost:9090/api/client';

  constructor(private http: HttpClient) { }

  getClientsPage(page: number = 0, size: number = 10): Observable<ClientDTO[]> {
    return this.http.get<ClientDTO[]>(`${this.apiUrl}/page?page=${page}&size=${size}`);
  }

  searchClientBySharedKey(sharedKey: string): Observable<ClientDTO[]> {
    return this.http.get<ClientDTO[]>(`${this.apiUrl}/${sharedKey}`);
  }

  searchClients(params: any = {}): Observable<ClientDTO[]> {
    return this.http.get<ClientDTO[]>(`${this.apiUrl}/search`, { params });
  }

  createClient(clientInput: any): Observable<Object> {
    return this.http.post<Object>(this.apiUrl, clientInput);
  }

}
