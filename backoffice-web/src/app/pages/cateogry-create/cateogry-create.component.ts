import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {switchMap} from 'rxjs';
import {Category} from 'src/app/dto/category';
import { ParentCategory } from 'src/app/dto/parent-category';
import {CategoryService} from 'src/app/services/category.service';

@Component({
  selector: 'app-cateogry-create',
  templateUrl: './cateogry-create.component.html',
  styleUrls: ['./cateogry-create.component.css'],
})
export class CateogryCreateComponent implements OnInit {
  ROOT_CATEGORY_NAME: string = 'root';

  showSuccess: boolean = false;
  showError: boolean = false;
  parentCatDdlOpts: ParentCategory[] = [];

  createCategoryForm = this.fb.group({
    name: ['', Validators.required],
    description: [''],
    parentCategory: [this.ROOT_CATEGORY_NAME],
  });

  constructor(
    private categoryService: CategoryService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.categoryService.findAllParents().subscribe((parentsRes) => {
      console.log(parentsRes)
      this.parentCatDdlOpts = parentsRes;
    });
  }

  create() {
    if (this.createCategoryForm.valid) {
      const categorySave: Category = this.createCategoryForm.value as Category;
      const selectedParentCategoryId =
        this.createCategoryForm.get('parentCategory')?.value || '';
      this.categoryService.getCategoryById(selectedParentCategoryId).pipe(
        switchMap((selectedParentCategory) => {
          categorySave.level = '0';
          if (
            selectedParentCategoryId !== 'root' &&
            selectedParentCategory.level === '0'
          ) {
            categorySave.level = '1';
            categorySave.parentLevel0 = {} as Category;
            categorySave.parentLevel0.id = selectedParentCategory.id;
            categorySave.parentLevel0.name = selectedParentCategory.name;
          } else if (
            selectedParentCategoryId !== 'root' &&
            selectedParentCategory.level === '1'
          ) {
            categorySave.level = '2';
            categorySave.parentLevel0 = {} as Category;
            categorySave.parentLevel0.id = selectedParentCategory.parentLevel0.id;
            categorySave.parentLevel0.name = selectedParentCategory.parentLevel0.name;
            categorySave.parentLevel1 = {} as Category;
            categorySave.parentLevel1.id = selectedParentCategory.id;
            categorySave.parentLevel1.name = selectedParentCategory.name;
          }
          return this.categoryService.create(categorySave);
        })
      ).subscribe({
        next: (c: Category) => {
          console.log('Created category successfully', c);
          this.createCategoryForm.reset();
          this.createCategoryForm.get('parentCategory')?.setValue('root');
          this.showSuccess = true;
          this.ngOnInit();
        },
        error: (e: Error) => {
          console.error('Create category error', e);
          this.showError = true;
        },
      });
    } else {
      this.createCategoryForm.markAllAsTouched();
    }
  }

  get name() {
    return this.createCategoryForm.get('name');
  }
}
