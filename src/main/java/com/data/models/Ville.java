package com.data.models;

import javax.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Objects;

@EntityScan
public class Ville {
    @Id
    private int Code_commune_INSEE;
    private String Nom_commune;
    private int Code_postal;
    private String Libelle_acheminement;
    private String Ligne_5;
    private double Latitude;
    private double Longitude;

    public Ville(){}

    @Override
    public String toString() {
        return "{" +
                "\"Code_commune_INSEE\": \"" + Code_commune_INSEE +
                "\", \"Nom_commune\": \"" + Nom_commune + '\"' +
                ", \"Code_postal\": \"" + Code_postal + '\"' +
                ", \"Libelle_acheminement\": \"" + Libelle_acheminement + '\"' +
                ", \"Ligne_5\": \"" + Ligne_5 + '\"' +
                ", \"Latitude\": \"" + Latitude +
                "\", \"Longitude\": \"" + Longitude +
                "\"}";
    }

    public int getCode_commune_INSEE() {
		return Code_commune_INSEE;
	}

	public void setCode_commune_INSEE(int code_commune_INSEE) {
		Code_commune_INSEE = code_commune_INSEE;
	}

	public String getNom_commune() {
		return Nom_commune;
	}

	public void setNom_commune(String nom_commune) {
		Nom_commune = nom_commune;
	}

	public int getCode_postal() {
		return Code_postal;
	}

	public void setCode_postal(int code_postal) {
		Code_postal = code_postal;
	}

	public String getLibelle_acheminement() {
		return Libelle_acheminement;
	}

	public void setLibelle_acheminement(String libelle_acheminement) {
		Libelle_acheminement = libelle_acheminement;
	}

	public String getLigne_5() {
		return Ligne_5;
	}

	public void setLigne_5(String ligne_5) {
		Ligne_5 = ligne_5;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return Double.compare(ville.getLongitude(), this.getLongitude()) == 0 && Double.compare(ville.getLatitude(), this.getLatitude()) == 0 && Objects.equals(ville.getCode_commune_INSEE(), this.getCode_commune_INSEE()) && Objects.equals(this.getNom_commune(), ville.getNom_commune()) && Objects.equals(this.getCode_postal(), ville.getCode_postal()) && Objects.equals(ville.getLibelle_acheminement(), this.getLibelle_acheminement()) && Objects.equals(this.getLigne_5(), ville.getLigne_5());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode_commune_INSEE(), this.getNom_commune(), this.getCode_postal(), this.getLibelle_acheminement(), this.getLigne_5(), this.getLatitude(), this.getLongitude());
    }
}
