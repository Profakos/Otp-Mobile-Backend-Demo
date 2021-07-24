package hu.otp.mobile.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.otp.mobile.core.domain.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

	Optional<UserToken> findByUserIdAndToken(int userId, String token);
}
