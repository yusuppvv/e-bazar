package com.company.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByEmailAndVisibilityTrue(String email);

    Optional<UsersEntity> findByEmailAndStatusAndVisibilityTrue(String email, Status status);

    Optional<UsersEntity> findByEmailAndVerifCodeAndStatusAndVisibilityTrue(String email, int code, Status status);

    Optional<UsersEntity> findByIdAndStatusAndVisibilityTrue(UUID userId, Status status);
}
