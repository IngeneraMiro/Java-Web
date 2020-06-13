package softuni.workshop.data.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends BaseClass implements GrantedAuthority {
    private String authority;

    public Role() {
    }

    public Role(String role) {
        this.authority = role;
    }

    @Column(name = "authority", unique = true, nullable = false)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}
