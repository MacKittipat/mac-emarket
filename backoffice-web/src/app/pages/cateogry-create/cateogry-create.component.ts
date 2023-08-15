import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray } from '@angular/forms';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-cateogry-create',
  templateUrl: './cateogry-create.component.html',
  styleUrls: ['./cateogry-create.component.css']
})
export class CateogryCreateComponent implements OnInit {

  createCategoryForm = this.fb.group({
    categoryL2: this.fb.array([])
  });

  constructor(
    private categoryService: CategoryService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
  }

  getCategoryL2() {
    return this.createCategoryForm.get('categoryL2') as FormArray;
  }

  add() {
    console.log('...');
    this.getCategoryL2().push(this.fb.group({name: '', description: ''}));
  }
}
