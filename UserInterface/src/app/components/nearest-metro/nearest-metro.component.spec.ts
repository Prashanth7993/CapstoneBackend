import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NearestMetroComponent } from './nearest-metro.component';

describe('NearestMetroComponent', () => {
  let component: NearestMetroComponent;
  let fixture: ComponentFixture<NearestMetroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NearestMetroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NearestMetroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
