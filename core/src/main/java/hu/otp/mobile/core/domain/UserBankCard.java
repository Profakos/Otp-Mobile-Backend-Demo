package hu.otp.mobile.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_bank_card")
public class UserBankCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private int userId;
	@Column
	private String cardId;
	@Column
	private String cardNumber;
	@Column
	private String cvc;
	@Column
	private String name;
	@Column
	private int amount;
	@Column
	private String currency;

}
