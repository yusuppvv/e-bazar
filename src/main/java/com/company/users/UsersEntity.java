package com.company.users;

import com.company.component.BaseMapper;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity extends BaseMapper{
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "verif_code", nullable = true)
    private int verifCode;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "location_id", nullable = true)
    private UUID locationId;
}
