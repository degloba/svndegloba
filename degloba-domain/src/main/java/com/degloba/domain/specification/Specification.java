package com.degloba.domain.specification;

/**
 * @category Interfície de les regles de negoci lligat al concepte de "normes" en DDD.</br>
 * És un patró de disseny de programació, que permet combinar regles de negoci encadenant les regles de negoci utilitzant lògica booleana.</br> 
 * Descriu una regla de negoci que es pot combinar amb altres regles de negoci.</br>
 * En aquest patró, una unitat de lògica de negoci hereda la seva funcionalitat de la classe abstracta d’especificació d’agregats abstractes.</br> 
 * La classe té una funció anomenada IsSatisfiedBy que retorna un valor booleà. 
 * Després de la instanciació, l'especificació està "encadenada" amb altres especificacions, cosa que fa que les noves especificacions es puguin mantenir fàcilment, 
 * però que segueixin una lògica de negoci altament personalitzable. 
 * A més, a l'instància, la lògica de negoci pot, mitjançant la invocació del mètode o IOC, alterar- el seu estat per convertir-se en un delegat d'altres classes, 
 * com ara un repositori de persistència.
 * 
 * 
 * @param <T> Type parameter que indica a quin tipus d'objecte s'aplica la especificació
 */
public interface Specification<T> {

  /**
   * Comprova si {@code t} satisfà l'especificació.
   *
   * @param t Objecte a testejar.
   * @return Si {@code t} cumpleix aquesta especificació retorna {@code true}.
   */
  boolean isSatisfiedBy(T t);

  /**
   * El resultat crea una nova {@link Specification}, 
   * la especificació present {@code this} i una altra {@code specification} crea una operació "and (AND)" 
   * @param specification Una altra especificació.
   * @return Una nova especificació.
   */
  Specification<T> and(Specification<T> specification);

  /**
   * Crea una nova especificació, la especificació present {@code this} i una altra {@code specification} crea una operació "or (OR)"
   * @param specification Una altra especificació.
   * @return Una nova especificació.
   */
  Specification<T> or(Specification<T> specification);

  /**
   * Crea una especificació, la operació resultant es un "non-(NOT)"
   * @return Una nova especificació.
   */
  Specification<T> not();
}
