package com.hhlt.konsultado.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.hhlt.konsultado.mapper.TokenMapper;
import com.hhlt.konsultado.service.TokenService;
import com.hhlt.konsultado.util.LoginUser;
import com.hhlt.konsultado.util.Token;
import com.hhlt.konsultado.util.TokenModel;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * token存到数据库的实现类
 * 
 * @author wujx
 *
 */
@Primary
@Service
public class TokenServiceDbImpl implements TokenService {
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceDbImpl.class);
	/**
	 * token过期秒数
	 */
	@Value("${token.expire.seconds}")
	private Integer expireSeconds;
	@Autowired
	private TokenMapper tokenMapper;

	/**
	 * 私钥
	 */
	@Value("${token.jwtSecret}")
	private String jwtSecret;

	private static Key KEY = null;
	private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

	/**
	 * 删除指定token
	 * @return
	 */
	public void delExpireToken(){
		tokenMapper.deleteExpiredToken();
	}

	@Override
	public Token saveToken(LoginUser loginUser) {
		loginUser.setToken(UUID.randomUUID().toString());
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);

		TokenModel model = new TokenModel();
		model.setId(loginUser.getToken());
		model.setCreateTime(new Date());
		model.setUpdateTime(new Date());
		model.setExpireTime(new Date(loginUser.getExpireTime()));
		model.setVal(JSONObject.toJSONString(loginUser));

		tokenMapper.save(model);

		String jwtToken = createJWTToken(loginUser);

		return new Token(jwtToken, loginUser.getLoginTime());
	}

	/**
	 * 生成jwt
	 * 
	 * @param loginUser
	 * @return
	 */
	private String createJWTToken(LoginUser loginUser) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(LOGIN_USER_KEY, loginUser.getToken());// 放入一个随机字符串，通过该串可找到登陆用户

		String jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance())
				.compact();

		return jwtToken;
	}

	@Override
	public void refresh(LoginUser loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);

		TokenModel model = tokenMapper.getById(loginUser.getToken());
		model.setUpdateTime(new Date());
		model.setExpireTime(new Date(loginUser.getExpireTime()));
		model.setVal(JSONObject.toJSONString(loginUser));

		tokenMapper.update(model);
	}

	@Override
	public LoginUser getLoginUser(String jwtToken) {
		String uuid = getUUIDFromJWT(jwtToken);
		if (uuid != null) {
			TokenModel model = tokenMapper.getById(uuid);
			return toLoginUser(model);
		}

		return null;
	}

	@Override
	public boolean deleteToken(String jwtToken) {
		String uuid = getUUIDFromJWT(jwtToken);
		if (uuid != null) {
			TokenModel model = tokenMapper.getById(uuid);
			LoginUser loginUser = toLoginUser(model);
			if (loginUser != null) {
				tokenMapper.delete(uuid);

				return true;
			}
		}

		return false;
	}

	private LoginUser toLoginUser(TokenModel model) {
		if (model == null) {
			return null;
		}
		return JSONObject.parseObject(model.getVal(), LoginUser.class);
	}

	private Key getKeyInstance() {
		if (KEY == null) {
			synchronized (TokenServiceJWTImpl.class) {
				if (KEY == null) {// 双重锁
					byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
					KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
				}
			}
		}

		return KEY;
	}

	private String getUUIDFromJWT(String jwt) {
		if ("null".equals(jwt) || StringUtils.isBlank(jwt)) {
			return null;
		}

		Map<String, Object> jwtClaims = null;
		try {
			jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
			return MapUtils.getString(jwtClaims, LOGIN_USER_KEY);
		} catch (ExpiredJwtException e) {
			logger.error("{}已过期", jwt);
		} catch (Exception e) {
			logger.error("{}", e);
		}

		return null;
	}

}
