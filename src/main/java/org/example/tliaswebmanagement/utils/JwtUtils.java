package org.example.tliaswebmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * JWT令牌工具类
 *
 * 改造说明：密钥和过期时间从 application.yml 读取，通过 static setter 注入到静态字段。
 * 这样所有调用处（LoginController / LoginCheckInterceptor / LoginCheckFilter）
 * 仍可用静态方法 JwtUtils.generateJwt() / JwtUtils.parseJWT() 访问，无需改动。
 *
 * 原理：Spring 容器启动时创建 JwtUtils 实例 → 调用 @Value 标注的 setter → 给 static 字段赋值
 *       此后静态方法即可使用配置文件中的值（请求处理发生在容器启动之后，时序安全）
 */
@Component
public class JwtUtils {

    private static String signKey;
    private static Long expire;

    /**
     * 通过 setter 注入将配置值赋给静态字段
     * 注意：setter 必须是非 static 的，Spring 才能通过实例调用它完成注入
     */
    @Value("${jwt.sign-key}")
    public void setSignKey(String signKey) {
        JwtUtils.signKey = signKey;
    }

    @Value("${jwt.expire}")
    public void setExpire(Long expire) {
        JwtUtils.expire = expire;
    }

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 生成的JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
