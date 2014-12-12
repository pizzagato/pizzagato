package fi.omapizzeria.pizzagatto.bean;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import fi.omapizzeria.pizzagatto.service.Salaaja;

/*
 * Koodi ei ole omaa luomusta, vaan kustomoitu Jaakon l‰hett‰m‰st‰ demosta
 */

/**
 * Olio, joka sis‰lt‰‰ k‰ytt‰j‰tunnuksen, suolan ja kryptatun ja suolatun salasanan
 * 
 */
public class AdminUser {

	private int id;
	private String username;
	private String salt;
	private String passwordHash;

	private static final String SALAUS_ALGORITMI = Salaaja.SHA512;
	private static final int SALAUS_KIERROKSIA = 100;

	public AdminUser(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		super();
		this.username = username;

		// generoidaan suola
		String suola = Salaaja.generoiSuola();
		setSalt(suola);
		// kryptataan salasana
		String kryptattuSalasana = Salaaja.salaa(password, getSalt(),
				SALAUS_ALGORITMI, SALAUS_KIERROKSIA);
		setPasswordHash(kryptattuSalasana);
	}
	
	public AdminUser(String username, String password, String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		super();
		this.username = username;
		setSalt(salt);


		// kryptataan salasana
		String kryptattuSalasana = Salaaja.salaa(password, getSalt(),
				SALAUS_ALGORITMI, SALAUS_KIERROKSIA);
		setPasswordHash(kryptattuSalasana);
	}

	public AdminUser(int id, String username, String salt, String passwordHash) {
		super();
		this.id = id;
		this.username = username;
		this.salt = salt;
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "WebUser [id=" + id + ", username=" + username + ", salt="
				+ salt + ", passwordHash=" + passwordHash + "]";
	}

	// kryptaa annetun salasanan t‰m‰n olion suolalla ja vertaa sit‰ t‰m‰n olion
	// salasanahashiin
	public boolean vertaaSalasanaa(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String kryptattuSalasana = Salaaja.salaa(password, getSalt(),
				SALAUS_ALGORITMI, SALAUS_KIERROKSIA);

		return kryptattuSalasana.equals(this.passwordHash);
	}

}
