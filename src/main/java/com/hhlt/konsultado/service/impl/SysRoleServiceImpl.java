package com.hhlt.konsultado.service.impl;


import com.hhlt.konsultado.entity.RoleDto;
import com.hhlt.konsultado.entity.SysRole;
import com.hhlt.konsultado.mapper.SysRoleMapper;
import com.hhlt.konsultado.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SysRoleMapper roleMapper;

	@Override
	@Transactional
	public void saveRole(RoleDto roleDto) {
		SysRole role = roleDto;
		List<Long> permissionIds = roleDto.getPermissionIds();
		permissionIds.remove(0L);

		if (role.getId() != null) {// 修改
			updateRole(role, permissionIds);
		} else {// 新增
			saveRole(role, permissionIds);
		}
	}

	private void saveRole(SysRole role, List<Long> permissionIds) {
		SysRole r = roleMapper.getRole(role.getName());
		if (r != null) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}

		roleMapper.insertSelective(role);
		if (!CollectionUtils.isEmpty(permissionIds)) {
            roleMapper.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("新增角色{}", role.getName());
	}

	private void updateRole(SysRole role, List<Long> permissionIds) {
		SysRole r = roleMapper.getRole(role.getName());
		if (r != null && r.getId() != role.getId()) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}

        roleMapper.updateByPrimaryKeySelective(role);

        roleMapper.deleteRolePermission(role.getId());
		if (!CollectionUtils.isEmpty(permissionIds)) {
            roleMapper.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("修改角色{}", role.getName());
	}

	@Override
	@Transactional
	public void deleteRole(Integer id) {
        roleMapper.deleteRolePermission(id);
        roleMapper.deleteRoleUser(id);
        roleMapper.deleteByPrimaryKey(id);

		log.debug("删除角色id:{}", id);
	}

    @Override
    public int count(Map<String, Object> params) {
		Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<String, Object> next = iterator.next();
			log.info(next.getKey()+":----->"+next.getValue());
		}

		return roleMapper.count(params);
    }

    @Override
    public List<SysRole> list(Map<String, Object> params, Integer offset, Integer limit) {
        return roleMapper.list(params,offset,limit);
    }

    @Override
    public SysRole getById(Long id) {
        return roleMapper.selectByPrimaryKey(id.intValue());
    }

    @Override
    public List<SysRole> listByUserId(Long userId) {
        return roleMapper.listByUserId(userId);
    }

}
