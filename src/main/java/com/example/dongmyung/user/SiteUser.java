package com.example.dongmyung.user;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    private String password;
    private Boolean isMember;

    public void updateMember(Boolean isMember) {
        this.isMember = isMember;
        System.out.println("업데이트");
    }
}
