package com.Ibase.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Ibase.model.IbaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class IbaseUserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;

	private String userName;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public IbaseUserDetailsImpl(String id, String userName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static IbaseUserDetailsImpl build(IbaseUser user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new IbaseUserDetailsImpl(
				user.getUserId(), 
				user.getUserName(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		IbaseUserDetailsImpl user = (IbaseUserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
