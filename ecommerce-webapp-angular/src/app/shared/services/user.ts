/** 
 * <div class="descripcio">Defineix un usuari de l'aplicació</div>
*/
export interface User {
   /** 
    * <div class="descripcio">id d'usuari</div> 
    */
   uid: string;
    /** 
     * <div class="descripcio">email d'usuari</div> 
     */
   email: string;
    /** 
     * <div class="descripcio">nom d'usuari</div> 
     */
   displayName: string;
    /** 
     * <div class="descripcio">foto d'usuari</div> 
     */
   photoURL: string;
    /** 
     * <div class="descripcio">esVerificat l'email d'usuari</div> 
     */
   emailVerified: boolean;
}