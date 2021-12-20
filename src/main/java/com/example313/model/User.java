package com.example313.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id", "roles"})
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    @NotBlank(message = "Enter your name")
    private String name;

    @NonNull
    @Column(name = "last_name", unique = true)
    @NotBlank(message = "Enter your login")
    private String lastName;

    @NonNull
    @Column(name = "age")
    private int age;

    @NonNull
    @Column(name = "email", unique = true)
    @NotBlank(message = "Enter your email")
    private String email;

    @NonNull
    @Column(name = "password")
    @NotBlank(message = "Enter your password")
    private String password;

    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_ID"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRolesNames() {
        return getRoles().toString().replaceAll("[\\p{Punct}&&[^,]]+", "");
    }
}