package com.hhlt.konsultado.service;


import com.hhlt.konsultado.entity.RoleDto;
import com.hhlt.konsultado.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Integer id);

	int count(Map<String, Object> params);

	List<SysRole> list(Map<String, Object> params, Integer offset, Integer limit);

	SysRole getById(Long id);

	List<SysRole> listByUserId(Long userId);
}
