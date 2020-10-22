package com.Ibase.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Ibase.model.IbaseFeedback;


@Repository
public interface IbaseFeedbackRepository extends MongoRepository<IbaseFeedback, String> {

	List<IbaseFeedback> findByProductId(String productId);

	Long countByProductId(String pro);

}
