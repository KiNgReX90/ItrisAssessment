import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.types';
import { Observable } from 'rxjs';
import { Slice } from '../models/paging.types';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:9090/users';
  constructor(private http: HttpClient) {}

  getUsersSlice(page: number, size: number): Observable<Slice<User>> { return this.http.get<Slice<User>>(`${this.apiUrl}?page=${page}&size=${size}`); }
  getUserById(id: number): Observable<User> { return this.http.get<User>(`${this.apiUrl}/${id}`); }
  createUser(user: User): Observable<User> { return this.http.post<User>(this.apiUrl, user); }
  updateUser(id: number, user: User): Observable<User> { return this.http.put<User>(`${this.apiUrl}/${id}`, user); }
  deleteUser(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
