package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

	void save(SysPermission permission);

	void update(SysPermission permission);

	void delete(Long id);

	List<SysPermission> listAll();

	List<SysPermission> listParents();

	List<SysPermission> listByRoleId(Long roleId);

	SysPermission getById(Long id);
}
