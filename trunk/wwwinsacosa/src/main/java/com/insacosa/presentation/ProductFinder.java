/**
 * 
 */
package com.insacosa.presentation;

import query.PaginatedResult;

/**
 * @author Slawek
 * 
 */
public interface ProductFinder {

    PaginatedResult<ProductListItemDto> findProducts(ProductSearchCriteria searchCriteria);
}
