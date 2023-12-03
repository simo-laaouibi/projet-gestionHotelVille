package entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Hotel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String adresse;
	private String telephone;
	
	@ManyToOne
	private Ville villes;
	
	public Hotel() {
		super();
	}
	

	public Hotel(String nom, String adresse, String telephone) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
	}


	public Hotel(String nom) {
		super();
		this.nom = nom;
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Ville getVille() {
		return villes;
	}

	public void setVille(Ville villes) {
		this.villes = villes;
	}


	public Hotel(String nom, String adresse, String telephone, Ville villes) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.villes = villes;
	}
	
	

}
