package cn.tedu.entity;

import lombok.Data;

/**
 * @功能 user实体类
 * @作者 Mr_hui
 * @日期 2023.6.15
 */
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    public User() {
    }

    public User(int id, String username, String password, String nickname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
