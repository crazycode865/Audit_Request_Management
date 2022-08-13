import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageComponent } from './message.component';
import { MessageService } from '../../services/message.service';
import { HttpClientModule } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';

describe('MessageComponent', () => {
  let component: MessageComponent;
  let fixture: ComponentFixture<MessageComponent>;

  const MockKeycloakService = {
    getKeycloakInstance: () => {
      return ['manger'];
    }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MessageComponent],
      imports: [HttpClientModule, MatDialogModule],

      providers: [
        MessageService,
        { provide: KeycloakService, useValue: MockKeycloakService },
        {
          provide: MatDialogRef,
          useValue: {}
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(MessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
