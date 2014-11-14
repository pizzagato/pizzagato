package fi.omapizzeria.pizzagatto.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/*
 * Koodi ei ole omaa luomusta, vaan kustomoitu Jaakon l�hett�m�st� demosta
 */

public class AdminUser {

	private int id;
	private String username;
	private String salt;
	private String passwordHash;

	private static final String SALAUS_ALGORITMI = Salaaja.SHA512;
	private static final int SALAUS_KIERROKSIA = 100;

	/**
	 * Luo uuden webuser-olion usernamella ja passwordilla. Generoi suolan ja
	 * hashaa salasanan automaattisesti k�ytt�en Salaaja-luokkaa.
	 * 
	 * @param username
	 *            K�ytt�j�tunnus
	 * @param password
	 *            Selkokielinen salasana
	 * @param password2
	 *            Selkokielinen salasana uudestaan
	 * @throws NoSuchAlgorithmException
	 *             Mik�li salausalgoritmia ei l�ydy
	 * @throws UnsupportedEncodingException
	 *             Mik�li suolaa tai hashia ei voida enkoodata tekstiksi
	 * @throws InvalidWebUserPoikkeus
	 *             Mik�li tietojen validointi ep�onnistui
	 */
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

	// kryptaa annetun salasanan t�m�n olion suolalla ja vertaa sit� t�m�n olion
	// salasanahashiin
	public boolean vertaaSalasanaa(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String kryptattuSalasana = Salaaja.salaa(password, getSalt(),
				SALAUS_ALGORITMI, SALAUS_KIERROKSIA);

		return kryptattuSalasana.equals(this.passwordHash);
	}

}
