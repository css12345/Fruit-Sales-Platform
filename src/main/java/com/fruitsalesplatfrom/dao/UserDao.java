package com.fruitsalesplatfrom.dao;

import com.fruitsalesplatform.entity.User;

public interface UserDao extends BaseDao<User> {
	public User getUserFromQQOpenId(String qqOpenId);
	public User getUserFromUserName(String userName);
}
