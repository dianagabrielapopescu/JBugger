export interface User {
  firstname: string;
  lastname: string;
  age: number;
  roles: UserRole[];
}

export enum UserRole {
  EDIT_BUG = 'edit_bug', CLOSE_BUG = 'close_bug', CREATE_BUG = 'create_bug'
}
