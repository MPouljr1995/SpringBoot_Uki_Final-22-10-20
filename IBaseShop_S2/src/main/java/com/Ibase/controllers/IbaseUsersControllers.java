package com.Ibase.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ibase.config.IbaseBasicAuthUtils;
import com.Ibase.model.IbaseERole;
import com.Ibase.model.IbaseRole;
import com.Ibase.model.IbaseUser;
import com.Ibase.model.request.IbaseSigninRequest;
import com.Ibase.model.request.IbaseSignupRequest;
import com.Ibase.model.response.IbaseBasicAuthResponse;
import com.Ibase.model.response.IbaseMessageResponse;
import com.Ibase.repository.IbaseRoleRepository;
import com.Ibase.repository.IbaseUserRepository;
import com.Ibase.services.IbaseUserDetailsImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class IbaseUsersControllers {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	IbaseBasicAuthUtils basicUtils;
	
	@Autowired
	IbaseUserRepository userRepository;
	
	@Autowired
	IbaseRoleRepository roleRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody IbaseSignupRequest signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.badRequest()
					.body(new IbaseMessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new IbaseMessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		IbaseUser user = new IbaseUser(signUpRequest.getUserName(), 
						 signUpRequest.getEmail(),
						 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		System.out.println(strRoles);
		Set<IbaseRole> roles = new HashSet<>();

		if (strRoles == null) {
			IbaseRole userRole = roleRepository.findByName(IbaseERole.USER_ROLE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					IbaseRole adminRole = roleRepository.findByName(IbaseERole.ADDMIN_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "shop":
					IbaseRole shopRole = roleRepository.findByName(IbaseERole.SHOP_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(shopRole);

					break;
				default:
					IbaseRole userRole = roleRepository.findByName(IbaseERole.USER_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setDate(new Date());
		userRepository.save(user);

		return ResponseEntity.ok(new IbaseMessageResponse ("User registered successfully!"));
	}
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody IbaseSigninRequest signinRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUserName(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = basicUtils.generateBasicToken(signinRequest.getUserName(), signinRequest.getPassword());
		
		IbaseUserDetailsImpl userDetails = (IbaseUserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new IbaseBasicAuthResponse(token, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@PostMapping(value="/logout")
	public ResponseEntity<?> logoutUser (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return ResponseEntity.ok(new IbaseMessageResponse("User logout successfully!"));
	}

}
