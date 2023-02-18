package com.holding.models;
// Generated 11 f�vr. 2023, 04:06:28 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Appartement generated by hbm2java
 */
@Entity
@Table(name = "appartement", catalog = "holding_bd")
public class Appartement implements java.io.Serializable {

	private Integer idAppartement;
	private int idImmeuble;
	private String libelle;
	private String adresse;
	private Integer superficie;
	private Integer emplacement;
	private String statutDisponibilite;
	private Date dateDisponibilite;
	private Float loyerMensuel;
	private String type;

	public Appartement() {
	}

	public Appartement(int idImmeuble) {
		this.idImmeuble = idImmeuble;
	}

	public Appartement(int idImmeuble, String libelle, String adresse, Integer superficie, Integer emplacement,
			String statutDisponibilite, Date dateDisponibilite, Float loyerMensuel, String type) {
		this.idImmeuble = idImmeuble;
		this.libelle = libelle;
		this.adresse = adresse;
		this.superficie = superficie;
		this.emplacement = emplacement;
		this.statutDisponibilite = statutDisponibilite;
		this.dateDisponibilite = dateDisponibilite;
		this.loyerMensuel = loyerMensuel;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_APPARTEMENT", unique = true, nullable = false)
	public Integer getIdAppartement() {
		return this.idAppartement;
	}

	public void setIdAppartement(Integer idAppartement) {
		this.idAppartement = idAppartement;
	}

	@Column(name = "ID_IMMEUBLE", nullable = false)
	public int getIdImmeuble() {
		return this.idImmeuble;
	}

	public void setIdImmeuble(int idImmeuble) {
		this.idImmeuble = idImmeuble;
	}

	@Column(name = "LIBELLE", length = 25)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Column(name = "ADRESSE", length = 25)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "SUPERFICIE")
	public Integer getSuperficie() {
		return this.superficie;
	}

	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	@Column(name = "EMPLACEMENT")
	public Integer getEmplacement() {
		return this.emplacement;
	}

	public void setEmplacement(Integer emplacement) {
		this.emplacement = emplacement;
	}

	@Column(name = "STATUT_DISPONIBILITE", length = 15)
	public String getStatutDisponibilite() {
		return this.statutDisponibilite;
	}

	public void setStatutDisponibilite(String statutDisponibilite) {
		this.statutDisponibilite = statutDisponibilite;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DISPONIBILITE", length = 10)
	public Date getDateDisponibilite() {
		return this.dateDisponibilite;
	}

	public void setDateDisponibilite(Date dateDisponibilite) {
		this.dateDisponibilite = dateDisponibilite;
	}

	@Column(name = "LOYER_MENSUEL", precision = 8)
	public Float getLoyerMensuel() {
		return this.loyerMensuel;
	}

	public void setLoyerMensuel(Float loyerMensuel) {
		this.loyerMensuel = loyerMensuel;
	}

	@Column(name = "TYPE", length = 25)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
