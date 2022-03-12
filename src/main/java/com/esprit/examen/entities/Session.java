package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Session implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@Temporal(TemporalType.DATE)
	private String dateDebut;
	//@Temporal(TemporalType.DATE)
	private String dateFin;
	private int duree;
	private String description;
	@ManyToOne
	@JoinColumn(name = "formateur_id",referencedColumnName = "id")
    Formateur formateur;
	@ManyToMany
	@JoinColumn(name = "cours_id",referencedColumnName = "id")
	private Set<Cours> cours;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	public Set<Cours> getCours() {
		return cours;
	}
	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", duree=" + duree
				+ ", description=" + description + "]";
	}
	public Session(int id, String dateDebut, String dateFin, int duree, String description) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.duree = duree;
		this.description = description;
	}

	public Session() {
		super();
	}

}
