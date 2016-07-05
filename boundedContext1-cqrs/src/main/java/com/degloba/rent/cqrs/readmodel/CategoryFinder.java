package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.Category;

public interface CategoryFinder {

    List<Category> findCategory();

}
