import { Component } from '@angular/core';

// users.interface.ts
export interface User {
  userId: string;
  name: string;
  email: string;
  phone: string;
  createdAt: Date;
}

// mock-users.ts
export const MOCK_USERS: User[] = [
  {
    userId: "USR001",
    name: "John Smith",
    email: "john.smith@example.com",
    phone: "9876543210",
    createdAt: new Date("2024-01-15")
  },
  {
    userId: "USR002",
    name: "Sarah Johnson",
    email: "sarah.j@example.com",
    phone: "9876543211",
    createdAt: new Date("2024-02-01")
  },
  {
    userId: "USR003",
    name: "Michael Brown",
    email: "michael.b@example.com",
    phone: "9876543212",
    createdAt: new Date("2024-02-15")
  },
  {
    userId: "USR004",
    name: "Emily Davis",
    email: "emily.d@example.com",
    phone: "9876543213",
    createdAt: new Date("2024-03-01")
  },
  {
    userId: "USR005",
    name: "David Wilson",
    email: "david.w@example.com",
    phone: "9876543214",
    createdAt: new Date("2024-03-15")
  }
];

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  users: User[] = [];
  loading: boolean = true;

  ngOnInit() {
    // Simulate API call delay
    setTimeout(() => {
      this.users = MOCK_USERS;
      this.loading = false;
    }, 1000);
  }
  
  loadUsers(): void {
    this.loading = true;
    // Implement your API call here
    // Example:
    // this.userService.getUsers().subscribe({
    //   next: (data) => {
    //     this.users = data;
    //     this.loading = false;
    //   },
    //   error: (error) => {
    //     console.error('Error:', error);
    //     this.loading = false;
    //   }
    // });
  }

  deleteUser(userId: string): void {
    // Implement your delete logic here
    // Example:
    // this.userService.deleteUser(userId).subscribe({
    //   next: () => {
    //     this.users = this.users.filter(user => user.userId !== userId);
    //   },
    //   error: (error) => {
    //     console.error('Error:', error);
    //   }
    // });
  }

  formatDate(date: Date): string {
    return new Date(date).toLocaleDateString();
  }

}
