package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.entity.SysUser;
import com.hhlt.konsultado.entity.UserDto;
import com.hhlt.konsultado.service.UserService;
import com.hhlt.konsultado.table.PageTableHandler;
import com.hhlt.konsultado.table.PageTableHandler.CountHandler;
import com.hhlt.konsultado.table.PageTableHandler.ListHandler;
import com.hhlt.konsultado.table.PageTableRequest;
import com.hhlt.konsultado.table.PageTableResponse;
import com.hhlt.konsultado.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");


	@Autowired
	private UserService userService;

	@PostMapping
	@PreAuthorize("hasAuthority('sys:user:add')")
	public SysUser saveUser(@RequestBody UserDto userDto) {
		SysUser u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}
		SysUser user=userService.getUser(userDto.getUsername());
		if(null!=user){
			userDto.setId(user.getId());
			return userService.updateUser(userDto);
		}
		return userService.saveUser(userDto);
	}


	@PutMapping
	@PreAuthorize("hasAuthority('sys:user:add')")
	public SysUser updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}


	@PutMapping(params = "headImgUrl")
	public void updateHeadImgUrl(String headImgUrl) {
		SysUser user = UserUtil.getLoginUser();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);

		userService.updateUser(userDto);
		log.debug("{}修改了头像", user.getUsername());
	}

	@PutMapping("/{username}")
	@PreAuthorize("hasAuthority('sys:user:password')")
	public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
		userService.changePassword(username, oldPassword, newPassword);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('sys:user:query')")
	public PageTableResponse listUsers(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return userService.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<SysUser> list(PageTableRequest request) {
				List<SysUser> list = userService.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@GetMapping("/current")
	public SysUser currentUser() {
		return UserUtil.getLoginUser();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:user:query')")
	public SysUser user(@PathVariable Long id) {
		return userService.getById(id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:user:del')")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

}
