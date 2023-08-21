import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Category } from 'src/app/dto/category';
import { CategoryService } from 'src/app/services/category.service';
import { TreeTableModule } from 'primeng/treetable';
import { TreeNode } from 'primeng/api';

@Component({
  selector: 'app-cateogry-list',
  templateUrl: './cateogry-list.component.html',
  styleUrls: ['./cateogry-list.component.css'],
})
export class CateogryListComponent implements OnInit {
  categories: Category[] = [];
  searchFields: string[] = ['id', 'name'];

  categoriesTreeNode!: TreeNode[];

  files!: TreeNode[];

  searchForm = this.fb.group({
    fieldName: ['id'],
    fieldValue: [''],
  });

  constructor(
    private categoryService: CategoryService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    let treeNode: TreeNode[] = [];
    this.categoryService.getCategories().subscribe((categories) => {
      this.categories = categories;

      let countL1: number = 0;
      for (const categoryL1 of this.categories === null ? [] : this.categories) {
        let dataL1 = {
          id: categoryL1.id,
          name: categoryL1.name,
        };
        let countL2: number = 0;
        let childrenL1: TreeNode[] = [];
        for(const categoryL2 of categoryL1.subCategories === null ? [] : categoryL1.subCategories) {
          let dataL2 = {
            id: categoryL2.id,
            name: categoryL2.name,
          };
          let childrenL2: TreeNode[] = [];
          for(const categoryL3 of categoryL2.subCategories === null ? [] : categoryL2.subCategories) {
            let dataL3 = {
              id: categoryL3.id,
              name: categoryL3.name,
            };
            childrenL2.push({data: dataL3, children: []});
          }
          childrenL1.push({data: dataL2, children: childrenL2});
          childrenL1[countL2].expanded = true;
          countL2++;
        }
        treeNode.push({ data: dataL1, children: childrenL1 });
        treeNode[countL1].expanded = true;
        countL1++;
      }

      this.categoriesTreeNode = treeNode;
    });


  }

  search() {
    console.log(this.searchForm.value);
  }

  delete(id: string) {
    console.log(id);
    this.categoryService.delete(id).subscribe(res => {
      this.categoriesTreeNode.map((category, i) => {
        if (id === category.data.id) {
          this.categoriesTreeNode.splice(i, 1);
          this.categoriesTreeNode = [...this.categoriesTreeNode];
        }
      });
    });

  }
}
