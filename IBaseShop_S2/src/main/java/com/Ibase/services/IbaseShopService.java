package com.Ibase.services;

import java.awt.print.Pageable;
import java.io.Console;

import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.Ibase.repository.IbaseShopRepository;
import com.Ibase.model.IbaseShop;

@Service
public class IbaseShopService {
	
	@Autowired
	IbaseShopRepository IbaseShopRepository;
	
	
	
	public ResponseEntity<Page<IbaseShop>> getAllShops(int pageNo, int pageSize){
		try {
			
			PageRequest pageable = PageRequest.of(pageNo, pageSize);
			Page<IbaseShop> shops = IbaseShopRepository.findAll(pageable);
			
			//System.out.println("hello");
			if(shops.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(shops,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<IbaseShop> getShopById(String shopId) {
		try {
			Optional<IbaseShop> shop =IbaseShopRepository.findById(shopId);
			if(shop.isPresent()) {
				return new ResponseEntity<>(shop.get() ,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<IbaseShop> createShop(IbaseShop shop) {
		shop.setCreateDate(new Date());
		try {
			IbaseShop sho = IbaseShopRepository.insert(shop);
			return new ResponseEntity<>(sho,HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public ResponseEntity<IbaseShop> updateShop(String shopId, IbaseShop newShop) {
		Optional <IbaseShop> oldShop = IbaseShopRepository.findById(shopId);
		if(oldShop.isPresent()) {
			IbaseShop _shop = oldShop.get();
			_shop.setOwnerId(newShop.getOwnerId());
			_shop.setShopName(newShop.getShopName());
			_shop.setDescription(newShop.getDescription());
			_shop.setAddress(newShop.getAddress());
			_shop.setTelephone(newShop.getTelephone());
			_shop.setShopLogo(newShop.getShopLogo());
			_shop.setRating(newShop.getRating());
			return new ResponseEntity<>(IbaseShopRepository.save(_shop),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



	public ResponseEntity<String> deleteShopById(String shopId) {
		Optional<IbaseShop> shop = IbaseShopRepository.findById(shopId);
		if(shop.isPresent()) {
			try {
				IbaseShopRepository.deleteById(shopId);
				return new ResponseEntity<>("SHOP DELETED", HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	

	public IbaseShop getShopId(String userId) {
		System.out.println("getShopId");
		return IbaseShopRepository.findByOwnerId(userId);
	}



	
	
	
}
