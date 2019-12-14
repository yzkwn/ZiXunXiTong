package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    SysRole getRole(String name);

    void saveRolePermission(@Param("roleId") Integer roleId, @Param("permissionIds") List<Long> permissionIds );

    void deleteRolePermission(Integer id);

    void deleteRoleUser(Integer id);

    int count(@Param("params") Map<String, Object> params);

    List<SysRole> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    SysRole getById(Long id);

    List<SysRole> listByUserId(Long userId);
}