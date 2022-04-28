package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.data.models.Ville;
import com.google.gson.Gson;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
//@RequestMapping(path="/JSON", produces="application/json")
public class VilleController {
	private Gson gson = new Gson();

	// fonction pour récupérer le contenu de la BDD
	@GetMapping(value="/ville")
	public String select(@RequestParam(required  = false, value="codePostal") String codePostal) {
		Connection connexion = null;
		ResultSet resultat = null;
		ArrayList<Ville> listVilles= new ArrayList<>();

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();

			//Trouver les identifiants des équipes
			String requete = null;
			if ( codePostal == null) {
				requete = "SELECT * FROM ville_france;";
			} else {
				requete = "SELECT * FROM ville_france WHERE Code_postal = "+codePostal+";";
			}
			try (Statement statement = connexion.createStatement()){
				resultat = statement.executeQuery(requete);
				
				while (resultat.next()) {
					Ville ville = new Ville();
					ville.setCodeCommuneINSEE(resultat.getInt("Code_commune_INSEE"));
					ville.setNomCommune(resultat.getString("Nom_commune"));
					ville.setCodePostal(resultat.getInt("Code_postal"));
					ville.setLibelleAcheminement(resultat.getString("Libelle_acheminement"));
					ville.setLigne5(resultat.getString("Ligne_5"));
					ville.setLatitude(resultat.getString("Latitude"));
					ville.setLongitude(resultat.getString("Longitude"));
					listVilles.add(ville);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listVilles.toString();
	}
	
	@PostMapping(value="/ville")
	public String insert(@RequestBody String request) {
		Ville ville = gson.fromJson(request, Ville.class);
		
		Connection connexion = null;

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();
			
			try (PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO ville_france VALUES(?,?,?,?,?,?,?);")){
				preparedStatement.setInt(1, ville.getCodeCommuneINSEE());
				preparedStatement.setString(2, ville.getNomCommune());
				preparedStatement.setInt(3, ville.getCodePostal());
				preparedStatement.setString(4, ville.getLibelleAcheminement());
				preparedStatement.setString(5, ville.getLigne5());
				preparedStatement.setString(6, ville.getLatitude());
				preparedStatement.setString(7, ville.getLongitude());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}
	     
	@PutMapping(value="/ville")
	public String update(@RequestBody String request) {
		Ville[] villes = gson.fromJson(request, Ville[].class);
		
		Connection connexion = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();
			
			//Trouver les identifiants des équipes
			try (PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE ville_france SET Code_commune_INSEE = ?, "
					+ "Nom_commune = ?, "
					+ "Code_postal=?, "
					+ "Libelle_acheminement=?, "
					+ "Ligne_5=?, "
					+ "Latitude=?, "
					+ "Longitude=? "
					+ "WHERE Code_commune_INSEE=?;")) {
				preparedStatement.setInt(1, villes[1].getCodeCommuneINSEE());
				preparedStatement.setString(2, villes[1].getNomCommune());
				preparedStatement.setInt(3, villes[1].getCodePostal());
				preparedStatement.setString(4, villes[1].getLibelleAcheminement());
				preparedStatement.setString(5, villes[1].getLigne5());
				preparedStatement.setString(6, villes[1].getLatitude());
				preparedStatement.setString(7, villes[1].getLongitude());
				preparedStatement.setInt(8, villes[0].getCodeCommuneINSEE());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@DeleteMapping(value="/ville")
	public void delete(@RequestParam(required  = false, value="Code_commune_INSEE") String codeCommuneINSEE) {
		Connection connexion = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();
			
			try (PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE=?;")){
				preparedStatement.setInt(1, Integer.parseInt(codeCommuneINSEE));
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}