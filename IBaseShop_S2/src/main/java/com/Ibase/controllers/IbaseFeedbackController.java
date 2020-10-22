package com.Ibase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ibase.model.IbaseFeedback;
import com.Ibase.services.IbaseFeedbackService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/feedback")
public class IbaseFeedbackController {
	
	@Autowired
	IbaseFeedbackService ibaseFeedbackService;
	
	@GetMapping
	public ResponseEntity<List<IbaseFeedback>> getAllFeedback(){
		return ibaseFeedbackService.getAllFeedback();
	}
	
	@PostMapping
	public ResponseEntity<IbaseFeedback> createFeedback(@RequestBody IbaseFeedback feedback){
		System.out.println("createFeedback");
		return ibaseFeedbackService.createFeedback(feedback);
	}
	
	@DeleteMapping("/{feedbackId}")
	public ResponseEntity<String> deleteFeedbackById(@PathVariable String feedbackId) {
		return ibaseFeedbackService.deleteFeedbackById(feedbackId);
	}
	
	@GetMapping("/{feedbackId}")
	public ResponseEntity<IbaseFeedback>getFeedbackById(@PathVariable String feedbackId){
		return ibaseFeedbackService.getFeedbackById(feedbackId);
	}
	
	@GetMapping(params="productId")
	public ResponseEntity<List<IbaseFeedback>> getFeedbackByProductId(@RequestParam String productId){
		return ibaseFeedbackService.getFeedbackByProductId(productId);
	}
	
	@GetMapping(params="pro")
	public ResponseEntity<Long> countFeedbackByProductId(@RequestParam String pro){
		return ibaseFeedbackService.countFeedbackByProductId(pro);
	}
}
