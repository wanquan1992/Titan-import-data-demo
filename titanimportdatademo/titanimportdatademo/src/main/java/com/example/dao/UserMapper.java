package com.example.dao;

import com.example.model.User;

public interface UserMapper {
	User selectUserById(int id);
}
