package com.holding.models;
// Generated 11 f�vr. 2023, 04:06:28 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Proprietaire generated by hbm2java
 */
@Entity
@Table(name = "proprietaire", catalog = "holding_bd")
public class Proprietaire implements java.io.Serializable {

	private Integer idProprietaire;
	private String nom;
	private String prenom;
	private String typePiece;
	private String numeroPiece;
	private String adresse;
	private Integer telephone;
	private String email;
	private byte[] photo;
	private String nomUtilisateur;
	private String motDePasse;

	public Proprietaire() {
	}

	public Proprietaire(String nom, String prenom, String typePiece, String numeroPiece, String adresse,
			Integer telephone, String email, byte[] photo, String nomUtilisateur, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.typePiece = typePiece;
		this.numeroPiece = numeroPiece;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.photo = photo;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_PROPRIETAIRE", unique = true, nullable = false)
	public Integer getIdProprietaire() {
		return this.idProprietaire;
	}

	public void setIdProprietaire(Integer idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	@Column(name = "NOM", length = 45)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "PRENOM", length = 45)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "TYPE_PIECE", length = 20)
	public String getTypePiece() {
		return this.typePiece;
	}

	public void setTypePiece(String typePiece) {
		this.typePiece = typePiece;
	}

	@Column(name = "NUMERO_PIECE", length = 20)
	public String getNumeroPiece() {
		return this.numeroPiece;
	}

	public void setNumeroPiece(String numeroPiece) {
		this.numeroPiece = numeroPiece;
	}

	@Column(name = "ADRESSE", length = 25)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "TELEPHONE")
	public Integer getTelephone() {
		return this.telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	@Column(name = "EMAIL", length = 25)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHOTO")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Column(name = "NOM_UTILISATEUR", length = 25)
	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	@Column(name = "MOT_DE_PASSE", length = 15)
	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}
