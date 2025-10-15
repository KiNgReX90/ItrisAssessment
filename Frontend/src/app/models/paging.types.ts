// Properties are derived from the Spring Data Slice interface.
export interface Slice<T> {
    content: T[];
    number: number;
    size: number;
    last: boolean;
}