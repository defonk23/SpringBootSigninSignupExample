package com.jackrutorial.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jackrutorial.model.Role;
import com.jackrutorial.model.User;
import com.jackrutorial.repository.RoleRepository;
import com.jackrutorial.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User findUserByEmail(String email) { 
		return userRepository.findByEmail(email);
	} 

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole))); 
		userRepository.save(user);
	}  
}
