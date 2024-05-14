import { Producto } from "./producto";

export interface Categoria {
  id: number,
  nombre: string,
  fotoUrl: string,
  productos: Producto[]
}
