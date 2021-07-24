package hu.otp.mobile.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.otp.mobile.core.domain.UserDevice;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer> {

	Optional<UserDevice> findByUserIdAndDeviceHash(int userId, String deviceHash);
}
