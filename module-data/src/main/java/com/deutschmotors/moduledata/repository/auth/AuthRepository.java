package com.deutschmotors.moduledata.repository.auth;

import com.deutschmotors.moduledata.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID>, AuthRepositoryCustom {

}
