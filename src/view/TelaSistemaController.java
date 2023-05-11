package view;


import java.io.IOException;

import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaSistemaController {

	@FXML
	private javafx.scene.control.Button btnSair;

	@FXML
	private javafx.scene.control.Button btnLivros;
	
	@FXML
	private javafx.scene.control.Button btnPerfil;
	
	@FXML
	private void ExibirLivro() throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TelaExibirLivros.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	@FXML
	private void Livros() throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TelaLivros.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			// fechar a janela
			Stage SairStage = (Stage) btnLivros.getScene().getWindow();
			SairStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	
	@FXML
	private void Sair() throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			DB.closeConnection();

			// fechar a janela
			Stage SairStage = (Stage) btnSair.getScene().getWindow();
			SairStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 



	@FXML
	private void Entrar() throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TelaSistema.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			// fechar a janela
			Stage SairStage = (Stage) btnPerfil.getScene().getWindow();
			SairStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	
	
//	@FXML
//	private void CriarLivro() throws IOException {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("TelaAddLivros.fxml"));
//			Scene scene = new Scene(root);
//			Stage stage = new Stage();
//			stage.setScene(scene);
//			stage.show();
//
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	} 



}
