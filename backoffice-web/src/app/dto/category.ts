export interface Category {
  id: string;
  name: string;
  level: number;
  subCategories: Category[];
  createdDateTime: string;
  updatedDateTime: string;
}
