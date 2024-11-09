import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-operators',
  templateUrl: './operators.component.html',
  styleUrl: './operators.component.css',
})
export class OperatorsComponent implements OnInit {
  operators: any = [];

  isLoading: boolean = false;

  constructor(private userService: UserService) {
    this.filteredUsers = this.users;
  }

  users: any[] = [
    {
      id: 153,
      name: 'BAC',
      email: 'BAC@ust.com',
      phone: '+93 6334234034874',
      role: 'OPERATOR',
      tickets: null,
    },
    {
      id: 154,
      name: 'DSFLKJ',
      email: 'SADFKLJL@ust.com',
      phone: '+93 98473473847',
      role: 'OPERATOR',
      tickets: null,
    },
  ];

  filteredUsers: any[] = [];
  searchText: string = '';

  ngOnInit(): void {}

  filterUsers(): void {
    if (!this.searchText) {
      this.filteredUsers = this.users;
      return;
    }

    const searchLower = this.searchText.toLowerCase();
    this.filteredUsers = this.users.filter(
      (user) =>
        user.name.toLowerCase().includes(searchLower) ||
        user.email.toLowerCase().includes(searchLower) ||
        user.phone.toLowerCase().includes(searchLower) ||
        user.role.toLowerCase().includes(searchLower)
    );
  }

  editUser(user: any): void {
    console.log('Editing user:', user);
    // Implement edit functionality
  }

  deleteUser(user: any): void {
    console.log('Deleting user:', user);
    // Implement delete functionality
  }

  getAllOperators(): any {
    this.isLoading = true;
    this.userService.getAllOperators().subscribe({
      next: (data) => {
        console.log(data);
        this.operators = data;
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }
}
