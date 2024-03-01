import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFormBaseComponent } from './client-form-base.component';

describe('ClientFormBaseComponent', () => {
  let component: ClientFormBaseComponent;
  let fixture: ComponentFixture<ClientFormBaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientFormBaseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientFormBaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
