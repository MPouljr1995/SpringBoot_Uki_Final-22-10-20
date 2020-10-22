package com.Ibase.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ibase.repository.IbaseFeedbackRepository;
import com.Ibase.model.IbaseFeedback;

@Service
public class IbaseFeedbackService {
	@Autowired
	IbaseFeedbackRepository ibaseFeedbackRepository;

	public ResponseEntity<List<IbaseFeedback>> getAllFeedback() {
		try {
			List<IbaseFeedback> feedbacks = ibaseFeedbackRepository.findAll();
			if(feedbacks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(feedbacks,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<IbaseFeedback> createFeedback(IbaseFeedback feedback) {
		feedback.setFeedbackDate(new Date());
		try {
			IbaseFeedback upFeedback = ibaseFeedbackRepository.insert(feedback);
			return new ResponseEntity<>(upFeedback ,HttpStatus.CREATED);
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<IbaseFeedback> getFeedbackById(String feedbackId) {
		try {
			Optional<IbaseFeedback> feedback = ibaseFeedbackRepository.findById(feedbackId);
			if(feedback.isPresent()) {
				return new ResponseEntity<>( feedback.get() , HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<IbaseFeedback>> getFeedbackByProductId(String productId) {
		try {
			List<IbaseFeedback> feedback = ibaseFeedbackRepository.findByProductId(productId);
			if(feedback.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(feedback ,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Long> countFeedbackByProductId(String pro) {
		try {
			Long totFeedback = ibaseFeedbackRepository.countByProductId(pro);
			if(totFeedback == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(totFeedback , HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> deleteFeedbackById(String feedbackId) {
		try {
			Optional<IbaseFeedback> feed = ibaseFeedbackRepository.findById(feedbackId);
			if (feed.isPresent()) {
				ibaseFeedbackRepository.deleteById(feedbackId);
				return new ResponseEntity<>("Feedback Deleted..", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
