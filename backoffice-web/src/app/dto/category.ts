export interface Category {
  id: string;
  name: string;
  level: number;
  parentLevel0: Category;
  parentLevel1: Category;
  createdDateTime: string;
  updatedDateTime: string;
  active: boolean;
}
