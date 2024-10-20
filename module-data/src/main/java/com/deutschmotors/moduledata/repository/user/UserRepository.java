package com.deutschmotors.moduledata.repository.user;

import com.deutschmotors.moduledata.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, UserRepositoryCustom {

}
