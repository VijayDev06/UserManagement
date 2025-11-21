package com.service;

import java.util.List;

import com.entity.User;

public interface UserService {

	User registerUserInService(User u);

	List<User>GetUserInService();

	User GetUserByIdInService(int id);

	User updateUserByIdInService(int id, User u);

	User deleteUserByIdInService(int id);

	void deleteAllUser();

	void deleteUserByIdsInService(List<Integer> userIds);

	List<User> getUsersByPage(int page, int size);

	List<User> getAllUserinpages(int page, int size);



	

}
