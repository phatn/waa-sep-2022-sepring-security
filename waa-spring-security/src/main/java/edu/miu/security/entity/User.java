package edu.miu.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Product product;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<OffensiveWordsAudit> offensiveWordsAudits;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<RequestLimiter> requestLimiters;
}
