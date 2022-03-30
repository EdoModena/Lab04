package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getNomeECognome(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			Studente s=null;

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				s= new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
			}
			rs.close();
			st.close();
			conn.close();
			
			return s;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean cerca(Corso corso, Studente s) {

		final String sql = "SELECT s.matricola,s.nome,s.cognome,s.CDS FROM studente s, iscrizione i WHERE i.matricola=s.matricola AND i.codins=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			List<Studente> studenti = new ArrayList<Studente>();
			Studente studente =null;

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				studente= new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				studenti.add(studente);
			}
			rs.close();
			st.close();
			conn.close();
			for(Studente stu: studenti) {
				if(stu.equals(s))
					return true;
			}
			return false;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}
