package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsodao;
	private StudenteDAO studentedao;
	
	public Model(){
		this.corsodao= new CorsoDAO();
		this.studentedao=new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return this.corsodao.getTuttiICorsi();
	}

	public Studente getNomeECognome(int matricola) {
		return this.studentedao.getNomeECognome(matricola);
	}
	
	public List<Studente> getStudentiByCorso(Corso corso){
		return this.corsodao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiPerStudente(int matricola){
		return this.corsodao.getCorsiPerStudente(matricola);
	}
	
	public boolean cerca(Corso corso, Studente s) {
		return this.studentedao.cerca(corso, s);
	}
}
