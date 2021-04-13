package com.example.apicommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.apicommon.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class JwtUtils {
    private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private static final String KEY = "changeIt";
    private static final String ISSUE = "zhangcy";

    //过期时间
    private static final long TOKEN_EXPIRE_TIME = 60000;

    private static final String USER_NAME = "username";

    /**
     * 生成token
     * @param account
     * @return
     */
    public String token(Account account){
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        String token = JWT.create()
                .withIssuer(ISSUE)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime()+TOKEN_EXPIRE_TIME))
                .withClaim(USER_NAME,account.getUsername())
                .sign(algorithm);
        log.info("generate token fro user:{}",account.getUsername());
        log.info("===================");
        log.info("generate token is:{}",token);
        return token;
    }

    /**
     * 校验token
     * @param username
     * @param token
     * @return
     */
    public boolean vertify(String username,String token){
        log.info("vertify jwt user :{}",username);
        log.info("===================");
        log.info("current token is:{}",token);
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        try{
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUE)
                    .withClaim(USER_NAME,username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            log.info("vertify jwt auth fail",e);
            return false;
        }
    }

}

