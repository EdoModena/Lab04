package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btncorsi;

    @FXML
    private Button btniscritti;

    @FXML
    private Button btniscrivi;

    @FXML
    private Button btnreset;

    @FXML
    private CheckBox chk;

    @FXML
    private ComboBox<String> cmbcorso;

    @FXML
    private TextField txtcognome;

    @FXML
    private TextField txtmatricola;

    @FXML
    private TextField txtnome;

    @FXML
    private TextArea txtrisultato;

    @FXML
    void handlechk(ActionEvent event) {
    	txtrisultato.clear();
    	String m=txtmatricola.getText();
    	int matricola;
    	try {
    		matricola=Integer.parseInt(m);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Inserisci una matricola");
    		return;
    	}
    	Studente s=model.getNomeECognome(matricola);
    	if(s==null) {
    		txtrisultato.setText("Inserisci matricola esistente");
    		return;
    	}
    	txtnome.setText(s.getNome());
    	txtcognome.setText(s.getCognome());
    }

    @FXML
    void handlecorsi(ActionEvent event) {
    	txtrisultato.clear();
    	String m=txtmatricola.getText();
    	int matricola;
    	try {
    		matricola=Integer.parseInt(m);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Inserisci una matricola");
    		return;
    	}
    	Studente s=model.getNomeECognome(matricola);
    	if(s==null) {
    		txtrisultato.setText("Inserisci matricola esistente");
    		return;
    	}
    	List<Corso> corsi= model.getCorsiPerStudente(matricola);
    	for(Corso c: corsi) {
    		txtrisultato.appendText(c + "\n");
    	}
    }

    @FXML
    void handleiscritti(ActionEvent event) {
    	txtrisultato.clear();
    	String nome=cmbcorso.getValue();
    	if(nome.equals("")) {
    		txtrisultato.setText("Scegli un corso");
    		return;
    	}
    	List<Corso> corsi = model.getTuttiICorsi();
    	Corso corso=null;
    	for(Corso c:corsi) {
    		if(c.getNome().compareTo(nome)==0) {
    			corso=c;
    		}
    	}
    	List<Studente> studenti=model.getStudentiByCorso(corso);
    	for(Studente s: studenti) {
    		txtrisultato.appendText(s + "\n");
    	}
    }

    @FXML
    void handleiscrivi(ActionEvent event) {
    	txtrisultato.clear();
    	String m=txtmatricola.getText();
    	String nome=cmbcorso.getValue();
    	int matricola;
    	if(nome.equals("")) {
    		txtrisultato.setText("Scegli un corso");
    		return;
    	}
    	try {
    		matricola=Integer.parseInt(m);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Inserisci una matricola");
    		return;
    	}
    	Studente s=model.getNomeECognome(matricola);
    	if(s==null) {
    		txtrisultato.setText("Inserisci matricola esistente");
    		return;
    	}
    	txtnome.setText(s.getNome());
    	txtcognome.setText(s.getCognome());
    	List<Corso> corsi = model.getTuttiICorsi();
    	Corso corso=null;
    	for(Corso c:corsi) {
    		if(c.getNome().compareTo(nome)==0) {
    			corso=c;
    		}
    	}
    	if(model.cerca(corso, s)) {
    		txtrisultato.setText("Studente già iscritto al corso");
    		return;
    	}
    	boolean isc=model.iscriviStudenteACorso(corso, s);
    	if(isc==true) {
    		txtrisultato.setText("Studente iscritto al corso!");
    	}
    	else {
    		txtrisultato.setText("Studente non iscritto al corso!");
    	}
    }

    @FXML
    void handlereset(ActionEvent event) {
    	txtmatricola.clear();
    	txtnome.clear();
    	txtcognome.clear();
    	txtrisultato.clear();
    }

    @FXML
    void initialize() {
        assert btncorsi != null : "fx:id=\"btncorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btniscritti != null : "fx:id=\"btniscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btniscrivi != null : "fx:id=\"btniscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnreset != null : "fx:id=\"btnreset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert chk != null : "fx:id=\"chk\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbcorso != null : "fx:id=\"cmbcorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtcognome != null : "fx:id=\"txtcognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtmatricola != null : "fx:id=\"txtmatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtnome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtrisultato != null : "fx:id=\"txtrisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }


	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
		cmbcorso.getItems().clear();
        List<Corso> corsi = model.getTuttiICorsi();
        for(Corso c: corsi) {
        	cmbcorso.getItems().add(c.getNome());
        }
        cmbcorso.getItems().add("");
	}
}