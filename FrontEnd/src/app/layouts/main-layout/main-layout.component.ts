import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent {

  currentPath: string = '';

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.currentPath = this.router.url;
    });
  }

  ngOnInit(): void {
    this.currentPath = this.router.url;
    console.log(this.currentPath)
  }

  toggleSidebar(): void {
    const sidebar = document.getElementById('sidebar-multi-level-sidebar');
    if (sidebar) {
      sidebar.classList.toggle('-translate-x-full');
    }
  }
}
