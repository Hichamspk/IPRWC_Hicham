package com.iprwc2.model;

import org.springframework.security.core.GrantedAuthority;

public class RightsAuthority implements GrantedAuthority {
    private final Rights right;

    public RightsAuthority(Rights right) {
        this.right = right;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + right.name();
    }
}
