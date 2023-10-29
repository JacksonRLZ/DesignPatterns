package com.biubiuq.designpatterns.adapter;

/**
 * @author biubiuQ
 * @description Login3rdTarget
 * @date 2023/10/29
 */
public interface Login3rdTarget {

    String loginByGitee(String code, String state);

    String loginByWechat(String... param);

    String loginByQQ(String... param);

}
