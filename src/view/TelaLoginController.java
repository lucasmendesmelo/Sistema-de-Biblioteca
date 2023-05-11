package view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import com.gluonhq.charm.glisten.control.Alert;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaLoginController {

	@FXML
	private com.gluonhq.charm.glisten.control.TextField txtNome;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private Button btnEntrar;



	@FXML
	public void Entrar() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String usuario = txtNome.getText(); // onde txtUsuario é o ID do campo de texto de usuário
		String senha = txtSenha.getText(); // onde txtSenha é o ID do campo de texto de senha

		if (usuarioDAO.validarLogin(usuario, senha) == true) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("TelaSistema.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();

				// fechar a janela de login
				Stage loginStage = (Stage) btnEntrar.getScene().getWindow();
				loginStage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// os dados estão incorretos, então podemos exibir uma mensagem de erro
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ErroLogin.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}