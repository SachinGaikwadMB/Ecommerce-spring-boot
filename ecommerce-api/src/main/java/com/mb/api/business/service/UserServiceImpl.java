package com.mb.api.business.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mb.api.business.exception.CustomException;
import com.mb.api.enums.ERole;
import com.mb.api.enums.ErrorCode;
import com.mb.api.jwt.JwtUtil;
import com.mb.api.persistance.entity.Role;
import com.mb.api.persistance.entity.User;
import com.mb.api.persistance.repository.RoleRepository;
import com.mb.api.persistance.repository.UserRepository;
import com.mb.api.web.model.LoginModel;
import com.mb.api.web.model.RegisterModel;
import com.mb.api.web.model.UpdateUserModel;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	private void saveNewUser(RegisterModel registerModel)
	{
		Set<Role> roles = new HashSet<>();

		User user = modelMapper.map(registerModel, User.class);
		user.setPassword(passwordEncoder.encode(registerModel.getPassword()));

		Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).orElseThrow(
				() -> new CustomException("Role not found !", ErrorCode.USER_NOT_FOUND));

		roles.add(userRole);
		user.setRoles(roles);
		try
		{
			userRepository.save(user);
		}
		catch (Exception ex)
		{
			throw new CustomException("Unable to save record !", ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String registerNewUser(RegisterModel registerModel)
	{
		if (userRepository.existsByUsername(registerModel.getUsername()) ||
				userRepository.existsByEmail(registerModel.getEmail()))
		{
			throw new CustomException(
					"User already exists with email or username " + ":: " + registerModel.getEmail() + " or " + registerModel.getUsername(),
					ErrorCode.USER_ALREADY_EXITS);
		}

		saveNewUser(registerModel);
		return "New User Register Successfully !";
	}

	private void saveNewAdmin(RegisterModel registerModel)
	{
		Set<Role> roles = new HashSet<>();

		User admin = modelMapper.map(registerModel, User.class);
		admin.setPassword(passwordEncoder.encode(registerModel.getPassword()));

		Role userRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN).orElseThrow(
				() -> new CustomException("Role not found !", ErrorCode.ADMIN_NOT_FOUND));

		roles.add(userRole);
		admin.setRoles(roles);
		try
		{
			userRepository.save(admin);
		}
		catch (Exception ex)
		{
			throw new CustomException("Unable to save record !", ErrorCode.INTERNAL_SERVER_ERROR);
		}

	}

	// Method to register new admin
	@Override
	public String registerNewAdmin(RegisterModel registerModel)
	{

		if (userRepository.existsByUsername(registerModel.getUsername()) ||
				userRepository.existsByEmail(registerModel.getEmail()))
		{
			throw new CustomException(
					"Admin already exists with email or username " + ":: " + registerModel.getEmail() + " or " + registerModel.getUsername(),
					ErrorCode.ADMIN_ALREADY_EXITS);
		}

		saveNewAdmin(registerModel);
		return "New Admin Register Successfully !";
	}

	@Override
	public Map<String, Object> login(LoginModel loginModel)
	{
		Map<String, Object> data = new HashMap<>();

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtil.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		data.put("email", loginModel.getEmail());
		data.put("roles", roles);
		data.put("message", "Login Successful!");
		data.put("jwtToken", jwtToken);

		return data;
	}

	@Override
	public String updateUser(UpdateUserModel updateUserModel)
	{
		User user = userRepository.findByEmail(updateUserModel.getEmail());
		if (user == null)
		{
			throw new CustomException("User not found with email " + updateUserModel.getEmail(), ErrorCode.NOT_FOUND);
		}

		user.setEmail(updateUserModel.getUpdatedEmail());

		try
		{
			userRepository.save(user);
		}
		catch (Exception ex)
		{
			throw new CustomException("unable to update user !", ErrorCode.INTERNAL_SERVER_ERROR);
		}

		return "User Updated Successfully !";
	}

}
