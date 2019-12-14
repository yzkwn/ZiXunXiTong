package com.hhlt.konsultado.service.impl;



import com.hhlt.konsultado.entity.SysPermission;
import com.hhlt.konsultado.entity.SysUser;
import com.hhlt.konsultado.mapper.SysPermissionMapper;
import com.hhlt.konsultado.service.UserService;
import com.hhlt.konsultado.util.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * spring security登陆处理
 * 
 * @author wujx
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger("adminLogger");
	@Autowired
	private UserService userService;
	@Autowired
	private SysPermissionMapper permissionMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userService.getUser(username);
		if (sysUser == null) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
		} else if (sysUser.getStatus() == SysUser.Status.LOCKED) {
			throw new LockedException("用户被锁定,请联系管理员");
		} else if (sysUser.getStatus() == SysUser.Status.DISABLED) {
			throw new DisabledException("用户已作废");
		}

		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(sysUser, loginUser);

		List<SysPermission> permissions = permissionMapper.listByUserId(sysUser.getId());
		loginUser.setPermissions(permissions);

		return loginUser;
	}

}
