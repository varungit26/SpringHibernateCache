package com.javaconfig.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaconfig.model.User;

@Repository
public interface UserDao {

	public List<User> list();

	public User get(int id);

	public void saveOrUpdate(User user);

	public void delete(int id);

	public void test();
}
