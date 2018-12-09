package com.fruitsalesplatform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.User;

public interface UserService {
	public User get(Serializable id);
	public List<User> find(@SuppressWarnings("rawtypes") Map map);
	public void insert(User user);
	public void update(User user);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public User getUserFromQQOpenId(String qqOpenId);
	public User getUserFromUserName(String userName);
}
