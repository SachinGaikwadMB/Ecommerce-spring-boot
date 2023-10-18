package com.mb.api.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mb.api.persistance.entity.User;
import com.mb.api.persistance.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = null;

		user = userRepository.findByEmail(username);
		if (user != null)
		{
			return UserDetailsImpl.build(user);
		}
		else
		{
			throw new UsernameNotFoundException(username + " not found");
		}
		
	}

	//Added for testing repos
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = null;

		user = userRepository.findByEmail(username);
		if (user != null)
		{
			return UserDetailsImpl.build(user);
		}
		else
		{
			throw new UsernameNotFoundException(username + " not found");
		}
		
	}
	
	
}
