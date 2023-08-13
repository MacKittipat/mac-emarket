import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/dto/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-cateogry-info',
  templateUrl: './cateogry-info.component.html',
  styleUrls: ['./cateogry-info.component.css'],
})
export class CateogryInfoComponent implements OnInit {

  category: Category = {} as Category;
  id: string = '';

  constructor(private categoryService: CategoryService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ?? '';
    this.categoryService.getCategoryById(this.id).subscribe((category) => {
      this.category = category;
    });
  }
}
