package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, nickname, phone,  email,status, createTime, updateTime) values(#{username}, #{password}, #{nickname},  #{phone}, #{email}, #{status}, now(), now())")
    int save(SysUser user);


    @Select("select * from sys_user t where t.username = #{username} ")
    SysUser getUser(String username);

    @Update("update sys_user t set t.password = #{password} where t.id = #{id}")
    int changePassword(@Param("id") Integer id, @Param("password") String password);

    Integer count(@Param("params") Map<String, Object> params);

    List<SysUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    @Delete("delete from sys_role_user where userId = #{userId}")
    int deleteUserRole(Integer userId);

    int saveUserRoles(@Param("userId") Integer userId, @Param("roleIds") List<Long> roleIds);

}