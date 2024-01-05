package com.iprwc2.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private String username;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Rights rights;

    public User(String name, Rights rights) {
        this.name = name;
        this.rights = rights;
    }

    public Rights getRights() {
        return rights;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Implementeer naar behoefte
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Pas aan naar je logica
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Pas aan naar je logica
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Pas aan naar je logica
    }

    @Override
    public boolean isEnabled() {
        return true; // Pas aan naar je logica
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", username='" + username + '\'' + ", name='" + name + '\'' + ", rights=" + rights + '}';
    }
}
