package com.brian.app.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.app.dao.CategoryRepository;
import com.brian.app.model.Category;
import com.brian.app.model.Product;

@Service
public class TestOnetoManyService {
@Autowired
CategoryRepository categoryRepository;
	@Transactional
	public void savecat()
{
	Category category = new Category("Computer");
    
    Product pc = new Product("DELL PC", "Quad-core PC", 1200, category);
     
    Product laptop = new Product("MacBook", "Apple High-end laptop", 2100, category);
     
    Product phone = new Product("iPhone 5", "Apple Best-selling smartphone", 499, category);
     
    Product tablet = new Product("iPad 3", "Apple Best-selling tablet", 1099, category);
     
    Set<Product> products = new HashSet<Product>();
    products.add(pc);
    products.add(laptop);
    products.add(phone);
    products.add(tablet);
     
    category.setProducts(products);
     
    categoryRepository.save(category);
}
}
