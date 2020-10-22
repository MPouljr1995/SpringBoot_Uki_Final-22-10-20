package com.Ibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ibase.model.IbaseUser;
import com.Ibase.repository.IbaseUserRepository;

@Service
public class IbaseUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	IbaseUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		IbaseUser user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return IbaseUserDetailsImpl.build(user);
	}
	
	

}
