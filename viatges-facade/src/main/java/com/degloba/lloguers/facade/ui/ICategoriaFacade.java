package com.degloba.lloguers.facade.ui;

import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Categoria;

/**
 * @category 
 * - Millora la legibilidad y la usabilidad de una biblioteca de software al enmascarar la interacción con componentes más complejos detrás de una API única (y a menudo simplificada)
 * - Proporcionar una interfaz específica de contexto para una funcionalidad más genérica (completa con validación de entrada específica de contexto)
 * - servir como punto de partida para un refactor más amplio de sistemas monolíticos o estrechamente acoplados a favor de un código más débilmente acoplado
 * 
 * @author degloba
 */
public interface ICategoriaFacade {

    void creaCategoria(Categoria categoria);

}
