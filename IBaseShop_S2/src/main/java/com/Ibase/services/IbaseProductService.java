package com.Ibase.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ibase.model.IbaseProduct;
import com.Ibase.repository.IbaseProductRepository;

@Service
public class IbaseProductService {
	@Autowired
	IbaseProductRepository ibaseProductRepository;

	public ResponseEntity<Map<String, Object>>getAllProducts(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
			Page<IbaseProduct> page = ibaseProductRepository.findAll(pageable);
			response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("Total no of elements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());
		    
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}

	public ResponseEntity<IbaseProduct> createProduct(IbaseProduct product) {
		product.setDate(new Date());
		product.setCount(1);
		try {
			return new ResponseEntity<>(ibaseProductRepository.insert(product),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Optional<IbaseProduct>> getProductById(String productId) {
		Optional<IbaseProduct> product = ibaseProductRepository.findById(productId);
		try {
			if (product.isPresent()) {
				return new ResponseEntity<>(product,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
	}

	public ResponseEntity<String> deleteProductById(String productId) {
		Optional<IbaseProduct> product = ibaseProductRepository.findById(productId);
		try {
			if (product.isPresent()) {
				ibaseProductRepository.deleteById(productId);
				return new ResponseEntity<>("PRODUCT DELETED",HttpStatus.OK);
			} else {
				return new ResponseEntity<>("PRODUCT NOT FOUND",HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}

	public ResponseEntity<IbaseProduct> updateProductById(IbaseProduct updateProduct, String productId) {
		Optional<IbaseProduct> oldProduct = ibaseProductRepository.findById(productId);
		try {
			if(oldProduct.isPresent()) {
				IbaseProduct _product = oldProduct.get();
				_product.setTitle(updateProduct.getTitle());
				_product.setDescription(updateProduct.getDescription());
				_product.setLastPrice(updateProduct.getLastPrice());
				_product.setSellPrice(updateProduct.getSellPrice());
				_product.setWarranty(updateProduct.getWarranty());
				_product.setRating(updateProduct.getRating());
				_product.setStock(updateProduct.getStock());
				_product.setBrand(updateProduct.getBrand());
				_product.setModel(updateProduct.getModel());
				_product.setImage_1(updateProduct.getImage_1());
				_product.setImage_2(updateProduct.getImage_2());
				_product.setImage_3(updateProduct.getImage_3());
				_product.setImage_4(updateProduct.getImage_4());
				_product.setImage_5(updateProduct.getImage_5());
				_product.setDescription(updateProduct.getDescription());
				
				return new ResponseEntity<>(ibaseProductRepository.save(_product),HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
	}

	public ResponseEntity<List<IbaseProduct>> getProductByShopId(String shopId) {
		List<IbaseProduct> shopProducts = ibaseProductRepository.findByShopId(shopId);
		try {
			if(shopProducts.isEmpty()) {
					return new ResponseEntity<>( HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(shopProducts ,HttpStatus.OK);
				}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<IbaseProduct>> getProductByTitle(String title) {
		List<IbaseProduct> product = ibaseProductRepository.findByTitleContaining(title);
		try {
			if (product.isEmpty()) {
				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(product ,HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


//	public ResponseEntity<List<IbaseProduct>> getProductByPriceBB(double minPrice, double maxPrice) {
//		Sort sort = Sort.by(Sort.Direction.DESC,"sellPrice");
//		List<IbaseProduct> product = ibaseProductRepository.findBySellPriceBetween(minPrice, maxPrice, sort);
//		try {
//			if (product.isEmpty()) {
//				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
//			} else {
//				return new ResponseEntity<>(product ,HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
//	public ResponseEntity<List<IbaseProduct>> getProductByPriceBB(double minPrice, double maxPrice) {
//		Sort sort = Sort.by(Sort.Direction.DESC,"sellPrice");
//		List<IbaseProduct> product = ibaseProductRepository.priceBetween(minPrice, maxPrice, sort);
//		try {
//			if (product.isEmpty()) {
//				return new ResponseEntity<>( HttpStatus.NO_CONTENT);
//			} else {
//				return new ResponseEntity<>(product ,HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


	public ResponseEntity<Map<String, Object>> getProductBySearch(String search, int pageNo, int pageSize,
			String sortBy,double minPrice, double maxPrice, int warranty) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
			Page<IbaseProduct> page = ibaseProductRepository.searchProducts(pageable, search,minPrice,maxPrice,warranty);
			response.put("data", page.getContent());
		    response.put("Total_no_of_pages", page.getTotalPages());
		    response.put("Total_no_of_elements", page.getTotalElements());
		    response.put("Current_page_no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	
//	public ResponseEntity<Map<String, Object>> getProductBySearch(String search, int pageNo, int pageSize,
//			String sortBy) {
//		try {
//			Map<String, Object> response = new HashMap<>();
//		    Sort sort = Sort.by(sortBy);
//			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//			Page<IbaseProduct> page = ibaseProductRepository.searchProducts(pageable, search);
//			response.put("data", page.getContent());
//		    response.put("Total no of pages", page.getTotalPages());
//		    response.put("Total no of elements", page.getTotalElements());
//		    response.put("Current page no", page.getNumber());
//		    
//		    return new ResponseEntity<>(response, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


//	public ResponseEntity<Map<String, Object>> getProductBySearch(String search, int pageNo, int pageSize,
//			String sortBy, double minPrice, double maxPrice, int warranty) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
