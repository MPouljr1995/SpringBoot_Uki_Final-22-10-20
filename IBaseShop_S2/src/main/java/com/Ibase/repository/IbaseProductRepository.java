package com.Ibase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Ibase.model.IbaseProduct;

@Repository
public interface IbaseProductRepository extends MongoRepository<IbaseProduct, String> {
	
	List<IbaseProduct> findByShopId(String shopId);

	List<IbaseProduct> findByTitleContaining(String title);

	List<IbaseProduct> findBySellPriceBetween(double minPrice, double maxPrice , Sort sort);
	
	@Query("{'sellPrice': {$lt : ?1, $gt : ?0}}")
	List<IbaseProduct>priceBetween(double minPrice, double maxPrice , Sort sort);
	
//	@Query("{'$and' : [ {'$or': [ {'title' : { $regex: ?0, $options: 'i'}}, {'model' : { $regex: ?0, $options: 'i'}} ]} ,{'sellPrice': {$lt : ?2, $gt : ?1}}, {'warranty': {$gte : ?3 }} ]}")
//	Page<IbaseProduct> searchProducts(Pageable pageable, String search, double minPrice, double maxPrice, int warranty);
	
	@Query("{'$or': [ {'title' : { $regex: ?0, $options: 'i'}}, {'model' : { $regex: ?0, $options: 'i'}} ] '$and': [{'sellPrice': {$lt : ?2, $gt : ?1}} , {'warranty': {$gte : ?3 }} ]}")
	Page<IbaseProduct> searchProducts(Pageable pageable, String search, double minPrice, double maxPrice, int warranty);
	
//	@Query("{'$or': [ {'title' : { $regex: ?0, $options: 'i'}}, {'model' : { $regex: ?0, $options: 'i'}},{'brand' : { $regex: ?0, $options: 'i'}},{'description' : { $regex: ?0, $options: 'i'}} ]}")
//	Page<IbaseProduct> searchProducts(Pageable pageable, String search);

	
}