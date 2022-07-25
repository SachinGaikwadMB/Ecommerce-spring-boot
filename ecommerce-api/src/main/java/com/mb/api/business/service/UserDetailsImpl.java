package com.mb.api.business.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mb.api.persistance.entity.User;

public class UserDetailsImpl implements UserDetails
{
	private static final long serialVersionUID = 1L;
	private String email;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String email, String password, Collection<? extends GrantedAuthority> authorities)
	{
		super();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	
	public static UserDetailsImpl build(User user)
	{
		List<GrantedAuthority> authority = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getUsername(), user.getPassword(), authority);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	@Override
	public String getUsername()
	{
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

}
