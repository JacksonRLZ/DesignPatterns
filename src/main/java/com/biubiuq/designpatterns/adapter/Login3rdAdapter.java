package com.biubiuq.designpatterns.adapter;

import com.alibaba.fastjson.JSONObject;
import com.biubiuq.designpatterns.entity.User;
import com.biubiuq.designpatterns.service.UserService;
import com.biubiuq.designpatterns.util.HttpClientUtils;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author biubiuQ
 * @description Login3rdAdapter
 * @date 2023/10/29
 */
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget {

    @Value("${gitee.state}")
    private String giteeState;

    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Override
    public String loginByGitee(String code, String state) {
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("Invalid state");
        }
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(tokenResponse.get("access_token"));
        String userUrl = giteeUserUrl.concat(token);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);
        String username = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String password = username;
        return autoRegister3rdAndLogin(username, password);

    }

    private String autoRegister3rdAndLogin(String username, String password) {
        if (super.checkUserExists(username)) {
            return super.login(username, password);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateDate(new Date());
        super.register(user);
        return super.login(username, password);
    }

    @Override
    public String loginByWechat(String... param) {
        return null;
    }

    @Override
    public String loginByQQ(String... param) {
        return null;
    }
}
