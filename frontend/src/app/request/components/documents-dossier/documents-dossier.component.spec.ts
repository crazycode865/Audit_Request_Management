import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentsDossierComponent } from './documents-dossier.component';

describe('DocumentsDossierComponent', () => {
  let component: DocumentsDossierComponent;
  let fixture: ComponentFixture<DocumentsDossierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DocumentsDossierComponent],
      imports: [HttpClientModule]
    }).compileComponents();

    fixture = TestBed.createComponent(DocumentsDossierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
