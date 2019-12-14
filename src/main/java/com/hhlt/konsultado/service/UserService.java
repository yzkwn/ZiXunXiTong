package com.hhlt.konsultado.service;


import com.hhlt.konsultado.entity.SysUser;
import com.hhlt.konsultado.entity.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

	void delete(Long id);

	int count(Map<String, Object> params);

	List<SysUser> list(Map<String, Object> params, Integer offset, Integer limit);

	SysUser getById(Long id);
}
