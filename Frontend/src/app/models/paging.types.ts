export interface Slice<T> {
    content: T[];
    number: number;
    size: number;
    last: boolean;
  }