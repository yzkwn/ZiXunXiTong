package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.SysPermission;
import com.hhlt.konsultado.mapper.SysPermissionMapper;
import com.hhlt.konsultado.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper  permissionMapper;
    private static final Logger log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Override
    public void save(SysPermission permission) {
        permissionMapper.insertSelective(permission);

        log.debug("新增菜单{}", permission.getName());
    }

    @Override
    public void update(SysPermission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteRolePermission(id);
        permissionMapper.deleteByPrimaryKey(id);
        permissionMapper.deleteByParentId(id);

    }

    @Override
    public List<SysPermission> listAll() {
        return permissionMapper.listAll();
    }

    @Override
    public List<SysPermission> listParents() {
        return permissionMapper.listParents();
    }

    @Override
    public List<SysPermission> listByRoleId(Long roleId) {
        return permissionMapper.listByRoleId(roleId);
    }

    @Override
    public SysPermission getById(Long id) {
        return permissionMapper.selectByPrimaryKey(id.intValue());
    }
}
