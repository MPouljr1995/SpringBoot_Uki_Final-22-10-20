package com.Ibase.controllers;

import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ibase.model.IbaseProduct;
import com.Ibase.services.IbaseProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class IbaseProductsControllers {
	@Autowired
	IbaseProductService ibaseProductService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllProducts(
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
    		@RequestParam(name = "pageSize", defaultValue = "10") int pageSize, 
    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		System.out.println("getAllProducts");
		return ibaseProductService.getAllProducts(pageNo,pageSize,sortBy);
	}
	
	@PostMapping
	public ResponseEntity<IbaseProduct> createProduct(@RequestBody IbaseProduct product) {
		System.out.println("poul");
		return ibaseProductService.createProduct(product);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Optional<IbaseProduct>> getProductById(@PathVariable String productId) {
		System.out.println("getProductById");
		return ibaseProductService.getProductById(productId);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable String productId) {
		return ibaseProductService.deleteProductById(productId);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<IbaseProduct> updateProductById(@RequestBody IbaseProduct updateProduct, @PathVariable String productId) {
		return ibaseProductService.updateProductById(updateProduct,productId);
	}
	
	@GetMapping("shop/{shopId}")
	public ResponseEntity<List<IbaseProduct>> getProductByShopId(@PathVariable String shopId) {
		return  ibaseProductService.getProductByShopId(shopId);
	}
	
//	@GetMapping( params="title")
//	public ResponseEntity<List<IbaseProduct>> getProductByTitle(@RequestParam String title){
//		return ibaseProductService.getProductByTitle(title);
//	}
	
//	@GetMapping( params= {"minPrice","maxPrice"} )
//	public ResponseEntity<List<IbaseProduct>> getProductByPriceBB(@RequestParam double minPrice , double maxPrice ){
//		System.out.println("getProductByPriceBB");
//		return ibaseProductService.getProductByPriceBB(minPrice, maxPrice);
//	}
	
	@GetMapping( params= {"search","minPrice","maxPrice","warranty"})
	public ResponseEntity<Map<String, Object>> getProductBySearch(
			@RequestParam String search,
			@RequestParam (name = "minPrice", defaultValue = "0")double minPrice,
			@RequestParam (name = "maxPrice", defaultValue = "100000")double maxPrice,
			@RequestParam(name = "warranty", defaultValue = "0") int warranty,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
    		@RequestParam(name = "pageSize", defaultValue = "20") int pageSize, 
    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		System.out.println("getProductBySearch");
		return ibaseProductService.getProductBySearch(search,pageNo,pageSize,sortBy,minPrice,maxPrice,warranty);
	}
	
//	@GetMapping(params= "search")
//	public ResponseEntity<Map<String, Object>> getProductBySearch(
//			@RequestParam String search,
//			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
//    		@RequestParam(name = "pageSize", defaultValue = "10") int pageSize, 
//    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
//		return ibaseProductService.getProductBySearch(search,pageNo,pageSize,sortBy);
//	}
	
	

}
