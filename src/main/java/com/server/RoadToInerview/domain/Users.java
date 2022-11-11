package com.server.RoadToInerview.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String salt;

    private boolean emailauth;

    private boolean manager;

    private String src;

}
