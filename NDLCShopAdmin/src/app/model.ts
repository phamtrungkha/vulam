export interface User {
  userId: number;
    name: string;
    email: string;
      password?: string;
    phone: string;
    gender: boolean | null;
      address: string;
      created_at: Date;
  }