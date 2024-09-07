package org.rdutta.identitymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.rdutta.identitymanager.enums.Roles;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "EMP_ID", unique = true, nullable = false)
    private UUID emp_id;
    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "SURNAME", nullable = false, length = 50)
    private String surname;
    @Column(name = "USERNAME", nullable = false,unique = true, length = 50)
    private String username;
    @Column(name = "PASSWORD", nullable = false, length = 68)
    private String password;
    @Column(name = "EMAIL", nullable = false, unique = true, length = 150)
    private String email;
    @Column(name = "PHONE", nullable = false, unique = true, length = 13)
    private String phone;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Roles role;
}
