package com.tcs.productcatrestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.productcatrestapi.exception.ProductIdNotFoundException;
import com.tcs.productcatrestapi.model.Product;
import com.tcs.productcatrestapi.service.ProductService;






@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ProductCat")
public class ProductController {
	@Autowired
	ProductService productService;
	
	
	
	
	@GetMapping 

public List<Product> getProducts() {
return productService.getProducts().get();
}

	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Product> getProductsBy(@PathVariable("id") int productId) throws ProductIdNotFoundException {
		Product product = productService.getProductById(productId).orElseThrow(()-> new ProductIdNotFoundException("Product Id not found"));
		
		
		return ResponseEntity.ok().body(product);
	}

	
	
	/**
@GetMapping("/{catName}")
	
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("catName")String catName) throws ProductIdNotFoundException {
		List<Product> product = productService.getProductsByCategory(catName).orElseThrow(()-> new ProductIdNotFoundException("Product not found"));
		product.forEach(p->{
			System.out.println(p) ;
			});
		
		return ResponseEntity.ok().body(product);
	}

	**/
	
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createProduct(@RequestBody Product product,UriComponentsBuilder uriComponentsBuilder,HttpServletRequest request) {
	
		Product product2 = productService.createProduct(product);
		UriComponents uriComponents = uriComponentsBuilder
				.path(request.getRequestURI()+"/{id}")
				.buildAndExpand(product2.getProductId());
		
		
	  return 	 ResponseEntity.created(uriComponents.toUri()).body(product2);
	}
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteProductById(@PathVariable int id) throws ProductIdNotFoundException { 
		Product product = productService.getProductById(id).orElseThrow(()-> new ProductIdNotFoundException("Product Id not found"));
		
		productService.deleteProduct(id);
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		
		return hashMap;
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id,
			@Valid @RequestBody Product product ) throws ProductIdNotFoundException {
		Product product2 = productService.getProductById(id)
				.orElseThrow(()-> new ProductIdNotFoundException("Product Id not found"));
		product.setProductId(id);
		Product product3 =productService.createProduct(product);
		
		return ResponseEntity.ok(product3);
	}
	
	
}
