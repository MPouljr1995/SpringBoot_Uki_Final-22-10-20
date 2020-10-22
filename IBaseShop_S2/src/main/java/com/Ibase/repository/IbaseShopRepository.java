package com.Ibase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Ibase.model.IbaseShop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface IbaseShopRepository extends MongoRepository<IbaseShop, String> {
	
	IbaseShop findByOwnerId(String userId);

}
