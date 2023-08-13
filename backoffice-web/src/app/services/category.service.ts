import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Category } from '../dto/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  getCategories() {
    return this.http.get<Category[]>(environment.apiUrl + 'categories');
  }

  getCategoryById(id: string) {
    return this.http.get<Category>(environment.apiUrl + 'categories/' + id);
  }
}
