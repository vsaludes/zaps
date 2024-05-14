import { Injectable } from '@angular/core';
import { Categoria } from '../interfaces/categoria';



@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  constructor() { }

  async getAll():Promise<Categoria[]>{
    const res = await fetch("./../../../../../backend/bbdd/database.json");
    const resJson = await res.json()
    return resJson
  }

}
