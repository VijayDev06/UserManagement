package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service    //(Stereotype annotation)
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUserInService(User u) {

		return userRepository.save(u);
	}

	@Override
	public List<User> GetUserInService() {

		return userRepository.findAll();
	}

	@Override
	public User GetUserByIdInService(int id) {

		return userRepository.findById(id).orElse(null); 
	}

	@Override
	public User deleteUserByIdInService(int id) {

		User u = userRepository.findById(id).orElse(null);

		if (u != null) {

			userRepository.delete(u);
			return u;
		}
		return null;
	}

	@Override
	public void deleteAllUser() {

		userRepository.deleteAll();
	}

	@Override
	public User updateUserByIdInService(int id, User u) {

		User updatedUser = userRepository.findById(id).orElse(null);

		if (updatedUser != null) {
			updatedUser.setUserName(u.getUserName());
			updatedUser.setContactNo(u.getContactNo());
			updatedUser.setAddress(u.getAddress());

			return userRepository.save(updatedUser);
		}
		return null;
	}

	@Override
	public void deleteUserByIdsInService(List<Integer> userIds) {
		
		
		 userRepository.deleteAllByIdInBatch(userIds);
		
	}

	@Override
	public List<User> getUsersByPage(int page, int size) {
		
		List<User> page1 = userRepository.findAll(PageRequest.of(page, size)).getContent();
		return page1;
	}

	@Override
	public List<User> getAllUserinpages(int page, int size) {
		
		Pageable p = PageRequest.of(page, size, Sort.by("userName", "aadharNumber"));
		Page<User> pagedResult = userRepository.findAll(p);
		
		return pagedResult.getContent();
	}

	

}
