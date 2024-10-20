package com.deutschmotors.moduledata.repository.user;

import com.deutschmotors.moduledata.entity.user.UserRegistrationInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationInformationRepository extends JpaRepository<UserRegistrationInformation, Long> {
    Optional<UserRegistrationInformation> findTop1ByOrderByIdDesc();
}
