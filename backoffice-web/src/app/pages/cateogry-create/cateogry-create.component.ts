import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators } from '@angular/forms';
import { concatMap } from 'rxjs';
import { Category } from 'src/app/dto/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-cateogry-create',
  templateUrl: './cateogry-create.component.html',
  styleUrls: ['./cateogry-create.component.css'],
})
export class CateogryCreateComponent implements OnInit {
  showSuccess: boolean = false;
  showError: boolean = false;

  allCategories: Category[] = [];
  parentCategories: Category[] = []

  createCategoryForm = this.fb.group({
    name: ['', Validators.required],
    description: [''],
    parentCategory: ['root'],
  });

  constructor(
    private categoryService: CategoryService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.categoryService.getCategories().subscribe((categories) => {
      this.allCategories = categories;

      this.allCategories.map((category) => {
        this.parentCategories.push({id : category.id , name: category.name} as Category);
      });

      console.log(this.parentCategories)

    });
  }

  create() {
    if (this.createCategoryForm.valid) {

      const category: Category = this.createCategoryForm.value as Category;
      let parentCategory: Category;
      const selectedParentCategoryId = this.createCategoryForm.get('parentCategory')?.value || '';
      if (selectedParentCategoryId !== 'root' && selectedParentCategoryId !== '') {
        this.categoryService.getCategoryById(selectedParentCategoryId).pipe(
          concatMap((selectedParentCategory) => {
            if (selectedParentCategory.subCategories.filter((subCat) => subCat.name.trim() != category.name.trim()).length > 0) {
              parentCategory = selectedParentCategory;
              parentCategory['subCategories'].push(category);
              return this.categoryService.update(parentCategory);
            } else {
              throw Error("Category with this name already exists");
            }
          })
        ).subscribe({
          next: (c) => {
            console.log('Created category successfully');
            this.createCategoryForm.reset();
            this.createCategoryForm.get('parentCategory')?.setValue('root');
            this.showSuccess = true;
          },
          error: (e) => {
            console.error(e);
            this.showError = true;
          },
        });
      } else {
        const category: Category = this.createCategoryForm.value as Category;
        this.categoryService.create(category).subscribe({
          next: (c) => {
            this.createCategoryForm.reset();
            this.createCategoryForm.get('parentCategory')?.setValue('root');
            this.showSuccess = true;
          },
          error: (e) => {
            this.showError = true;
          },
        });
      }

    } else {
      this.createCategoryForm.markAllAsTouched();
    }
  }

  get name() {
    return this.createCategoryForm.get('name');
  }
}
