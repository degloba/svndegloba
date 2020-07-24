/** Classe Hotel */
export class Hotel {
  id: number;
  nom = '';
  email = "";
     
   
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}