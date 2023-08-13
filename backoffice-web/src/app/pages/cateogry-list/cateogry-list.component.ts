import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Category } from 'src/app/dto/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-cateogry-list',
  templateUrl: './cateogry-list.component.html',
  styleUrls: ['./cateogry-list.component.css']
})
export class CateogryListComponent implements OnInit  {

  categories: Category[] = [];
  searchFields: string[] = ['id', 'name'];

  searchForm = this.fb.group({
    fieldName: ['id'],
    fieldValue: ['']
  });

  constructor(private categoryService: CategoryService, private fb: FormBuilder) { }

  ngOnInit() {
      this.categoryService.getCategories().subscribe(categories => {
        this.categories = categories;
        console.log(this.categories)
      });
  }

  search() {
    console.log(this.searchForm.value);
  }

}
