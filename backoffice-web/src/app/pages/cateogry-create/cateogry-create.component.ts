import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators } from '@angular/forms';
import { Observable, Observer, concatMap } from 'rxjs';
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
  parentCategories: Category[] = [];

  categoryLevel: number = 0;

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
        this.parentCategories.push({
          id: category.id,
          name: category.name,
        } as Category);

        category.subCategories?.map((categoryL1) => {
          this.parentCategories.push({
            id: categoryL1.id,
            name: category.name + ">>" + categoryL1.name,
          } as Category);
        });

      });
    });
  }

  create() {
    if (this.createCategoryForm.valid) {
      const category: Category = this.createCategoryForm.value as Category;
      let parentCategory: Category;
      const selectedParentCategoryId =
        this.createCategoryForm.get('parentCategory')?.value || '';


      if (selectedParentCategoryId !== 'root' && !selectedParentCategoryId.includes('>>')) {
        this.categoryLevel = 1;
      } else if (selectedParentCategoryId !== 'root' && selectedParentCategoryId.includes('>>')) {
        this.categoryLevel = 2;
      }
      console.log(selectedParentCategoryId + "===" + this.categoryLevel)

      if (
        selectedParentCategoryId !== 'root' &&
        selectedParentCategoryId !== ''
      ) {
        this.categoryService
          .getCategoryById(selectedParentCategoryId)
          .pipe(
            concatMap((selectedParentCategory) => {
              parentCategory = selectedParentCategory;
              category.level = this.categoryLevel;
              if (selectedParentCategory.subCategories === null) {
                parentCategory.subCategories = [];
                parentCategory['subCategories'].push(category);
                return this.categoryService.update(parentCategory);
              } else if (
                selectedParentCategory.subCategories.filter(
                  (subCat) => subCat.name.trim() != category.name.trim()
                ).length > 0
              ) {
                parentCategory['subCategories'].push(category);
                return this.categoryService.update(parentCategory);
              } else {
                throw Error('Category with this name already exists');
              }
            })
          )
          .subscribe(this.onCreateCategory());
      } else {
        const category: Category = this.createCategoryForm.value as Category;
        category.level = this.categoryLevel;
        this.categoryService
          .create(category)
          .subscribe(this.onCreateCategory());
      }
    } else {
      this.createCategoryForm.markAllAsTouched();
    }
  }

  onCreateCategory() {
    return {
      next: (c: Category) => {
        console.log('Created category successfully');
        console.log(c);
        this.createCategoryForm.reset();
        this.createCategoryForm.get('parentCategory')?.setValue('root');
        this.showSuccess = true;
        if (this.categoryLevel === 0) {
          this.parentCategories.push({ id: c.id, name: c.name } as Category);
        } else if (this.categoryLevel === 1) {
          this.parentCategories.push({ id: c.subCategories[c.subCategories.length-1].id,
            name: c.subCategories[c.subCategories.length-1].name } as Category);
        }
      },
      error: (e: Error) => {
        console.error(e);
        this.showError = true;
      },
    };
  }

  get name() {
    return this.createCategoryForm.get('name');
  }
}
