import { Categoria } from './../interfaces/categoria';
import { Injectable } from '@angular/core';
import { Producto } from '../interfaces/producto';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  // constructor() { }

  //  async getByCategoria(id:number):Promise<Producto[]>{
    //  const res = await fetch("./../../../assets/data/database.json");
    //  const resJson:Categoria[] = await res.json()
    //  const productos = resJson.find(categoria => categoria.id === id)?.productos;
    //  if(productos) return productos;
    //  return [];
  //  }

  private jsonUrl = 'assets/data/database.json';

  constructor(private http: HttpClient) { }

  getData(): Observable<any[]> {
    return this.http.get<any[]>(this.jsonUrl);
  }

  getDataByCategory(id: number): Observable<any[]> {
    return this.getData().pipe(
      map(items => items.filter(item => item.id === id))
    );
  }

  
}
