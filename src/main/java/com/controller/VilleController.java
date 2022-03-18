package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;

@RestController
public class VilleController {

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public String get(@RequestParam(required  = false, value="codePostal") String codePostal) {
		System.out.println("get");
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		String villeDescription = "";

		try {
			System.out.println("Je rentre dans le try");
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();
			System.out.println("Connexion get");

			//Trouver les identifiants des équipes
			String requete = "SELECT * FROM ville_france WHERE Code_postal = "+codePostal+";";
			statement = connexion.createStatement();
			resultat = statement.executeQuery(requete);
			System.out.println("Requête envoyé");
			
			while (resultat.next()) {
				String code_commune = resultat.getString("Code_commune_INSEE");
				String nom_commune = resultat.getString("Nom_commune");
				villeDescription += "<p>Code commune INSEE : "+code_commune+" Nom commune : "+nom_commune+"</p>";
				System.out.println("Code commune INSEE : "+code_commune+"\nNom commune : "+nom_commune+"\n");
			}
		} catch (SQLException e) {
			System.out.println("ça merde");
		}

		return villeDescription;
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	public String getInsert(@RequestBody String request) {
		System.out.println(request);
		
		String[] requestParams;
		String Code_commune_INSEE = "";
		String Nom_commune = "";
		String Code_postal = "";
		String Libelle_acheminement = "";
		String Ligne_5 = "";
		String Latitude = "";
		String Longitude = "";
		
		requestParams = request.split("&");
		for (String requestParam : requestParams) {
			String nomVariable = requestParam.split("=")[0];
			String valeurVariable = requestParam.split("=")[1];
			switch (nomVariable) {
			case "Code_commune_INSEE" :
				Code_commune_INSEE = valeurVariable;
				break;
			case "Nom_commune" :
				Nom_commune = valeurVariable;
				break;
			case "Code_postal" :
				Code_postal = valeurVariable;
				break;
			case "Libelle_acheminement" :
				Code_postal = valeurVariable;
				break;
			case "Ligne_5" :
				Code_postal = valeurVariable;
				break;
			case "Latitude" :
				Code_postal = valeurVariable;
				break;
			case "Longitude" :
				Code_postal = valeurVariable;
				break;
			}
		}
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			connexion = daoFactory.getConnection();

			//Trouver les identifiants des équipes
			preparedStatement = connexion.prepareStatement("INSERT INTO ville_france VALUES('?','?','?','?','?','?','?');");
			preparedStatement.setString(1, Code_commune_INSEE);
			preparedStatement.setString(2, Nom_commune);
			preparedStatement.setString(3, Code_postal);
			preparedStatement.setString(4, Libelle_acheminement);
			preparedStatement.setString(5, Ligne_5);
			preparedStatement.setString(6, Latitude);
			preparedStatement.setString(7, Longitude);
			preparedStatement.executeUpdate();
			System.out.println("Requête envoyé");
		} catch (SQLException e) {
			System.out.println("ça merde");
		}

		return request;
	}
	     

}