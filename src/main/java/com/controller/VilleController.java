package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.data.models.Ville;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
//@RequestMapping(path="/JSON", produces="application/json")
public class VilleController {

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public String select(@RequestParam(required  = false, value="codePostal") String codePostal) {
		System.out.println("Requête get");
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Ville ville = null;

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();

			//Trouver les identifiants des équipes
			String requete = "SELECT * FROM ville_france WHERE Code_postal = "+codePostal+";";
			statement = connexion.createStatement();
			resultat = statement.executeQuery(requete);
			System.out.println("Requête select envoyé");
			
			while (resultat.next()) {
				ville = new Ville();
				ville.setCode_commune_INSEE(resultat.getInt("Code_commune_INSEE"));
				ville.setNom_commune(resultat.getString("Nom_commune"));
				ville.setCode_postal(resultat.getInt("Code_postal"));
				ville.setLibelle_acheminement(resultat.getString("Libelle_acheminement"));
				ville.setLigne_5(resultat.getString("Ligne_5"));
				ville.setLatitude(resultat.getDouble("Latitude"));
				ville.setLongitude(resultat.getDouble("Longitude"));
				
				System.out.println(ville.toString());
			}
		} catch (SQLException e) {
			System.out.println("Requête INSERT échouée : "+e.getMessage());
		}
		return ville.toString();
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	public String insert(@RequestBody String request) {
		Map<String, Object> response = null;
		try {
			response = new ObjectMapper().readValue(request, HashMap.class);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		Ville ville = new Ville();
		
		for (Map.Entry mapentry : response.entrySet()) {
			switch (mapentry.getKey().toString()) {
			case "Code_commune_INSEE" :
				ville.setCode_commune_INSEE(Integer.parseInt(mapentry.getValue().toString()));
				break;
			case "Nom_commune" :
				ville.setNom_commune(mapentry.getValue().toString());
				break;
			case "Code_postal" :
				ville.setCode_postal(Integer.parseInt(mapentry.getValue().toString()));
				break;
			case "Libelle_acheminement" :
				ville.setLibelle_acheminement(mapentry.getValue().toString());
				break;
			case "Ligne_5" :
				ville.setLigne_5(mapentry.getValue().toString());
				break;
			case "Latitude" :
				ville.setLatitude(Double.parseDouble(mapentry.getValue().toString()));
				break;
			case "Longitude" :
				ville.setLongitude(Double.parseDouble(mapentry.getValue().toString()));
				break;
			}
		}
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		System.out.println(ville.toString());

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();
			System.out.println("Connexion à la base de donnée établie");
			
			//Trouver les identifiants des équipes
			preparedStatement = connexion.prepareStatement("INSERT INTO ville_france VALUES(?,?,?,?,?,?,?);");
			preparedStatement.setInt(1, ville.getCode_commune_INSEE());
			preparedStatement.setString(2, ville.getNom_commune());
			preparedStatement.setInt(3, ville.getCode_postal());
			preparedStatement.setString(4, ville.getLibelle_acheminement());
			preparedStatement.setString(5, ville.getLigne_5());
			preparedStatement.setDouble(6, ville.getLatitude());
			preparedStatement.setDouble(7, ville.getLongitude());
			preparedStatement.executeUpdate();
			System.out.println("Requête INSERT envoyé");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return request;
	}
	     

}