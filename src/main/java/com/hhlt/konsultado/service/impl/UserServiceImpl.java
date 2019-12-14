package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.SysUser;
import com.hhlt.konsultado.entity.UserDto;
import com.hhlt.konsultado.mapper.SysUserMapper;
import com.hhlt.konsultado.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public SysUser saveUser(UserDto userDto) {
		SysUser user = userDto;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(SysUser.Status.VALID);
		userMapper.save(user);
		saveUserRoles(user.getId(), userDto.getRoleIds());

		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	private void saveUserRoles(Integer userId, List<Long> roleIds) {
		if (roleIds != null) {
			userMapper.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userMapper.saveUserRoles(userId, roleIds);
			}
		}
	}

	@Override
	public SysUser getUser(String username) {
		return userMapper.getUser(username);
	}

	@Override
	public void changePassword(String username, String oldPassword, String newPassword) {
		SysUser u = userMapper.getUser(username);
		if (u == null) {
			throw new IllegalArgumentException("用户不存在");
		}

		if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
			throw new IllegalArgumentException("旧密码错误");
		}

		userMapper.changePassword(u.getId(), passwordEncoder.encode(newPassword));

		log.debug("修改{}的密码", username);
	}


	@Override
	public void delete(Long id) {
		userMapper.deleteByPrimaryKey(id.intValue());
		userMapper.deleteUserRole(id.intValue());
	}

	@Override
	public int count(Map<String, Object> params) {
		return userMapper.count(params);
	}

	@Override
	public List<SysUser> list(Map<String, Object> params, Integer offset, Integer limit) {
		return userMapper.list(params,offset,limit);
	}

	@Override
	public SysUser getById(Long id) {
		return userMapper.selectByPrimaryKey(id.intValue());
	}

	@Override
	@Transactional
	public SysUser updateUser(UserDto userDto) {
		userMapper.updateByPrimaryKeySelective(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());

		return userDto;
	}

}
