package kz.careerguidance.applicationapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static kz.careerguidance.applicationapi.utils.AuthoritiesConstants.USER;


@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role = USER;

    @Column(name = "location", nullable = false)
    private String location;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<History> history;

}
