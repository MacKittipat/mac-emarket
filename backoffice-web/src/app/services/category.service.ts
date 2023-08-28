import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Category } from '../dto/category';
import { ParentCategory } from '../dto/parent-category';

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

  findAllParents() {
    return this.http.get<ParentCategory[]>(environment.apiUrl + 'categories/parents');
  }

  create(category: Category) {
    return this.http.post<Category>(environment.apiUrl + 'categories', category);
  }

  update(category: Category) {
    return this.http.put<Category>(environment.apiUrl + 'categories', category);
  }

  delete(id: string) {
    return this.http.delete(environment.apiUrl + 'categories/' + id);
  }
}
