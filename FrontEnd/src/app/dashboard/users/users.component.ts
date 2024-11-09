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

  deleteUser(userId: number): void {
    this.userService.deleteUserById(userId).subscribe({
      next: () => {
        console.log("User Deleted Successfully")
      },
      error: (error) => {
        console.error('Error:', error);
      },
      complete:()=>{
        this.loadUsers();
      }
    });
  }

 

}
