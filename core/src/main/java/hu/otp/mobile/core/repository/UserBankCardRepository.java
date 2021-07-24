package hu.otp.mobile.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.otp.mobile.core.domain.UserBankCard;

@Repository
public interface UserBankCardRepository extends JpaRepository<UserBankCard, Integer> {

	Optional<UserBankCard> findByUserIdAndCardId(int userId, String cardId);
}
