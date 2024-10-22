package com.hcc.entities;

import com.hcc.enums.AuthorityEnum;
import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private AuthorityEnum authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor to initialize the authority field
    public Authority(AuthorityEnum authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public Authority() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority.toString();
    }

    public void setAuthority(AuthorityEnum authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
