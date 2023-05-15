package vapeShop.entity.users;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
