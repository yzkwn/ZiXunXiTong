package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> listByUserId(Integer id);

    void deleteRolePermission(Long id);

    void deleteByParentId(Long id);

    List<SysPermission> listAll();

    List<SysPermission> listParents();

    List<SysPermission> listByRoleId(Long roleId);

    void save(SysPermission permission);

}