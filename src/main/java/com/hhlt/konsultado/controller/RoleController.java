package com.hhlt.konsultado.controller;


import com.google.common.collect.Maps;
import com.hhlt.konsultado.entity.RoleDto;
import com.hhlt.konsultado.entity.SysRole;
import com.hhlt.konsultado.service.SysRoleService;
import com.hhlt.konsultado.table.PageTableHandler;
import com.hhlt.konsultado.table.PageTableHandler.CountHandler;
import com.hhlt.konsultado.table.PageTableHandler.ListHandler;
import com.hhlt.konsultado.table.PageTableRequest;
import com.hhlt.konsultado.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色相关接口
 * 
 * @author wujx
 *
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private SysRoleService roleService;

	@PostMapping
	@PreAuthorize("hasAuthority('sys:role:add')")
	public void saveRole(@RequestBody RoleDto roleDto) {
		roleService.saveRole(roleDto);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('sys:role:query')")
	public PageTableResponse listRoles(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return roleService.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<SysRole> list(PageTableRequest request) {
				List<SysRole> list = roleService.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:role:query')")
	public SysRole get(@PathVariable Long id) {
		return roleService.getById(id);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
	public List<SysRole> roles() {
		return roleService.list(Maps.newHashMap(), null, null);
	}

	@GetMapping(params = "userId")
	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
	public List<SysRole> roles(Long userId) {
		return roleService.listByUserId(userId);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:role:del')")
	public void delete(@PathVariable Long id) {
		roleService.deleteRole(id.intValue());
	}
}
