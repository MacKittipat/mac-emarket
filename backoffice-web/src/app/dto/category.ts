export interface Category {
  id: string;
  name: string;
  subCategories: Category[];
  createdDateTime: string;
  updatedDateTime: string;
}
