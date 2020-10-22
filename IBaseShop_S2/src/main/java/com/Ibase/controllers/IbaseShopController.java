package com.Ibase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


import com.Ibase.model.IbaseShop;
import com.Ibase.services.IbaseShopService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/shops")
public class IbaseShopController {
	
	@Autowired
	IbaseShopService IbaseShopService;
	
	@GetMapping
	public ResponseEntity<Page<IbaseShop>> getAllShops(
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize){
		return IbaseShopService.getAllShops(pageNo,pageSize);
	}
	
	@GetMapping("/{shopId}")
	public ResponseEntity<IbaseShop> getShopById(@PathVariable String shopId){
		return IbaseShopService.getShopById(shopId);
	}
	
	@PostMapping
	public ResponseEntity<IbaseShop> createShop( @RequestBody IbaseShop shop){
		System.out.println("create");
		return IbaseShopService.createShop(shop);
	}
	
	@PutMapping("/{shopId}")
	public ResponseEntity<IbaseShop> updateShop(@RequestBody IbaseShop shop , @PathVariable String shopId){
		//System.out.println("hii");
		return IbaseShopService.updateShop(shopId,shop);
	}
	
	@DeleteMapping("/{shopId}")
	public ResponseEntity<String> deleteShop(@PathVariable String shopId) {
		return IbaseShopService.deleteShopById(shopId);
	}
	
	@GetMapping(params = {"userId"})
	public IbaseShop getShopId(@RequestParam String userId) {
		return IbaseShopService.getShopId(userId);
	}
	
	
	
}
