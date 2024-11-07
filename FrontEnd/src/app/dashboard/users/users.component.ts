import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

// users.interface.ts
export interface User {
  id: any;
  name: string;
  email: string;
  phone: string;
  createdAt: Date;
  role:string;
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  constructor(private userService:UserService){

  }

  users: User[] = [];
  loading: boolean = true;

  ngOnInit() {
    this.loadUsers()
  }
  
  loadUsers(): void {
    this.loading = true;
    this.userService.getAllUsers().subscribe({
      next: (data) => {
        this.users = data;
        console.log(data)
        this.loading = false;
      },
      error: (error) => {
        console.error('Error:', error);
        this.loading = false;
      },
      complete:()=>{
        this.loading=false
      }
    });
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

 

}
