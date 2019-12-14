package com.hhlt.konsultado.mapper;


import com.hhlt.konsultado.util.TokenModel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TokenMapper {

	@Insert("insert into t_token(id, val, expireTime, createTime, updateTime) values (#{id}, #{val}, #{expireTime}, #{createTime}, #{updateTime})")
	int save(TokenModel model);

	@Select("select * from t_token t where t.id = #{id}")
	TokenModel getById(String id);

	@Update("update t_token t set t.val = #{val}, t.expireTime = #{expireTime}, t.updateTime = #{updateTime} where t.id = #{id}")
	int update(TokenModel model);

	@Delete("delete from t_token where id = #{id}")
	int delete(String id);
	@Delete("delete from t_token where expireTime<now()")
	int deleteExpiredToken();
}
