package opt.mobile.common.exceptions;

public enum MobileErrorMessage {

	/** User token does not exist */
	CORE_USER_TOKEN_DOESNT_EXIST("A felhasználói token nem szerepel", 10050),

	/** User token expired or undecodeable */
	CORE_USER_TOKEN_EXPIRED_OR_UNDECODABLE("A felhasználói token lejárt vagy nem értelmezhető", 10051),

	/** User does not exist */
	CORE_USER_DOESNT_EXIST("A felhasználó nem létezik", 10060),

	/** User email address incorrect */
	CORE_USER_EMAIL_INCORRECT("A felhasználó email címe hibás", 10060),

	/** Device does not belong to the user */
	CORE_DEVICE_DOES_NOT_BELONG_TO_USER("Ez az eszköz a felhasználóhoz tartozik", 10070),

	/** Bank card does not belong to the user */
	CORE_BANK_CARD_DOES_NOT_BELONG_TO_USER("Ez a bankkártya nem ehhez a felhasználóhoz tartozik", 10100),

	/** User does not have enough money to purchase the ticket */
	CORE_USER_NOT_ENOUGH_MONEY("A felhasználónak nincs elegendő pénze hogy megvásárolja a jegyet!", 10101),

	/** Event does not exist */
	TICKET_EVENT_DOESNT_EXIST("Nem létezik ilyen esemény!", 20001),

	/** Seat does not exist */
	TICKET_SEAT_DOESNT_EXIST("Nem létezik ilyen szék!", 20002),

	/** Can not sell tickets to an event that has already begun */
	TICKET_SEAT_ALREADY_RESERVED("Már lefoglalt székre nem lehet jegyet eladni!!", 20010),

	/** Can not sell tickets to an event that has already begun */
	TICKET_EVENT_ALREADY_BEGUN("Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!", 20011),

	/** External system unavailable */
	TICKET_EXTERNAL_SYSTEM_UNAVAILABLE("A külső rendszer nem elérhető!", 20404),

	/** Card validation failed */
	TICKET_CARD_VALIDATION_FAILED("A kártya validálása sikertelen", 20405),

	/** Event does not exist */
	PARTNER_EVENT_DOESNT_EXIST("Nem létezik ilyen esemény!", 90001),

	/** Seat does not exist */
	PARTNER_SEAT_DOESNT_EXIST("Nem létezik ilyen szék!", 90002),

	/** Seat is already reserved */
	PARTNER_SEAT_ALREADY_RESERVED("Már lefoglalt székre nem lehet jegyet eladni!", 90010);

	private String label;
	private int errorCode;

	private MobileErrorMessage(String label, int errorCode) {

		this.label = label;
		this.errorCode = errorCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
