package com.hhlt.konsultado.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hhlt.konsultado.entity.SysPermission;
import com.hhlt.konsultado.mapper.SysPermissionMapper;
import com.hhlt.konsultado.service.SysPermissionService;
import com.hhlt.konsultado.util.LoginUser;
import com.hhlt.konsultado.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/permissions")
public class SysPermissionController {

	@Autowired
	private SysPermissionService permissionService;

	@GetMapping("/current")
	public List<SysPermission> permissionsCurrent() {
		LoginUser loginUser = UserUtil.getLoginUser();
		List<SysPermission> list = loginUser.getPermissions();
		final List<SysPermission> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

		setChild(permissions);

		return permissions.stream().filter(p -> p.getParentid().equals(0)).collect(Collectors.toList());
	}

	private void setChild(List<SysPermission> permissions) {
		permissions.parallelStream().forEach(per -> {
						List<SysPermission> child = permissions.stream().filter(p -> p.getParentid().equals(per.getId()))
								.collect(Collectors.toList());
						per.setChild(child);
		});
	}

	/**
	 * 菜单列表
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param list
	 */
	private void setPermissionsList(Integer pId, List<SysPermission> permissionsAll, List<SysPermission> list) {
		for (SysPermission per : permissionsAll) {
			if (per.getParentid().equals(pId)) {
				list.add(per);
				if (permissionsAll.stream().filter(p -> p.getParentid().equals(per.getId())).findAny() != null) {
					setPermissionsList(per.getId(), permissionsAll, list);
				}
			}
		}
	}

	@GetMapping
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public List<SysPermission> permissionsList() {
		List<SysPermission> permissionsAll = permissionService.listAll();

		List<SysPermission> list = Lists.newArrayList();
		setPermissionsList(0, permissionsAll, list);

		return list;
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public JSONArray permissionsAll() {
		List<SysPermission> permissionsAll = permissionService.listAll();
		JSONArray array = new JSONArray();
		setPermissionsTree(0, permissionsAll, array);

		return array;
	}

	@GetMapping("/parents")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public List<SysPermission> parentMenu() {
		List<SysPermission> parents = permissionService.listParents();

		return parents;
	}

	/**
	 * 菜单树
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param array
	 */
	private void setPermissionsTree(Integer pId, List<SysPermission> permissionsAll, JSONArray array) {
		for (SysPermission per : permissionsAll) {
			if (per.getParentid().equals(pId)) {
				String string = JSONObject.toJSONString(per);
				JSONObject parent = (JSONObject) JSONObject.parse(string);
				array.add(parent);

				if (permissionsAll.stream().filter(p -> p.getParentid().equals(per.getId())).findAny() != null) {
					JSONArray child = new JSONArray();
					parent.put("child", child);
					setPermissionsTree(per.getId(), permissionsAll, child);
				}
			}
		}
	}

	@GetMapping(params = "roleId")
	@PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
	public List<SysPermission> listByRoleId(Long roleId) {
		return permissionService.listByRoleId(roleId);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('sys:menu:add')")
	public void save(@RequestBody SysPermission permission) {
		permissionService.save(permission);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public SysPermission get(@PathVariable Long id) {
		return permissionService.getById(id);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('sys:menu:add')")
	public void update(@RequestBody SysPermission permission) {
		permissionService.update(permission);
	}

	/**
	 * 校验权限
	 * 
	 * @return
	 */
	@GetMapping("/owns")
	public Set<String> ownsPermission() {
		List<SysPermission> permissions = UserUtil.getLoginUser().getPermissions();
		if (CollectionUtils.isEmpty(permissions)) {
			return Collections.emptySet();
		}

		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(SysPermission::getPermission).collect(Collectors.toSet());
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:menu:del')")
	public void delete(@PathVariable Long id) {
		permissionService.delete(id);
	}
}
