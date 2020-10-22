package com.Ibase.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Ibase.model.IbaseOrder;

@Repository
public interface IbaseOrderRepository extends MongoRepository<IbaseOrder , String> {

//	List<IbaseOrder> findByShopId(String shopId);



}
