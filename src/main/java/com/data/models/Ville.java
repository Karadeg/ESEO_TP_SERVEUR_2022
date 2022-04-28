package com.data.models;

import javax.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Objects;

@EntityScan
public class Ville {
    @Id
    private int codeCommuneINSEE;
    private String nomCommune;
    private int codePostal;
    private String libelleAcheminement;
    private String ligne5;
    private String latitude;
    private String longitude;

    public Ville(){
    	//Nothing to do
    }

    @Override
    public String toString() {
        return "{" +
                "\"Code_commune_INSEE\": \"" + codeCommuneINSEE +
                "\", \"Nom_commune\": \"" + nomCommune + '\"' +
                ", \"Code_postal\": \"" + codePostal + '\"' +
                ", \"Libelle_acheminement\": \"" + libelleAcheminement + '\"' +
                ", \"Ligne_5\": \"" + ligne5 + '\"' +
                ", \"Latitude\": \"" + latitude +
                "\", \"Longitude\": \"" + longitude +
                "\"}";
    }

    public int getCodeCommuneINSEE() {
		return codeCommuneINSEE;
	}

	public void setCodeCommuneINSEE(int codeCommuneINSEE) {
		this.codeCommuneINSEE = codeCommuneINSEE;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}

	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}

	public String getLigne5() {
		return ligne5;
	}

	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return Objects.equals(this.getLatitude(), ville.getLatitude()) && Objects.equals(this.getLongitude(), ville.getLongitude()) && Objects.equals(ville.getCodeCommuneINSEE(), this.getCodeCommuneINSEE()) && Objects.equals(this.getNomCommune(), ville.getNomCommune()) && Objects.equals(this.getCodePostal(), ville.getCodePostal()) && Objects.equals(ville.getLibelleAcheminement(), this.getLibelleAcheminement()) && Objects.equals(this.getLigne5(), ville.getLigne5());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCodeCommuneINSEE(), this.getNomCommune(), this.getCodePostal(), this.getLibelleAcheminement(), this.getLigne5(), this.getLatitude(), this.getLongitude());
    }
}
