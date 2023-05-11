package view;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.LivroDAO;
import db.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Livro;

public class TelaLivroController {

	@FXML
	private TableView<Livro> tbLivros;
	@FXML
	private TableColumn<Livro, String> clTitulo;
	@FXML
	private TableColumn<Livro, String> clAutor;
	@FXML
	private TableColumn<Livro, Integer> clAno;
	@FXML
	private TableColumn<Livro, Integer> clId;

	@FXML
	private TextField txtTitulo;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtAno;
	@FXML
	private TextField txtTituloPesquisar;

	@FXML
	private javafx.scene.control.Button btnAdicionar;
	@FXML
	private javafx.scene.control.Button btnPesquisar;

	List<Livro> listaLivros = new ArrayList<>();
	

	private ObservableList<Livro> observableListLivros;

	@FXML
	private void ExibirTelaCriarLivro() throws IOException {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("TelaAddLivros.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void adicionarLivro() {
		Livro novoLivro = new Livro(txtTitulo.getText(), txtAutor.getText(), Integer.parseInt(txtAno.getText()));
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.inserirLivro(novoLivro);

		// Limpa as TextFields após inserção do livro
		txtTitulo.setText("");
		txtAutor.setText("");
		txtAno.setText("");
	}

	public ArrayList<Livro> listarLivros() {
		Connection conexao = DB.getConnection();
		LivroDAO livroDAO = new LivroDAO();
		List<Livro> lista = LivroDAO.listarLivros(conexao);
		ArrayList<Livro> arrayList = new ArrayList<>(lista);
		return arrayList;
	}

	private ObservableList<Livro> searchResults = FXCollections.observableArrayList(); // criar a lista fora do método

	@FXML
	private void pesquisarLivroTest() {
		Connection conexao = DB.getConnection();
		List<Livro> listaLivro = LivroDAO.listarLivros(conexao);

		ObservableList<Livro> searchResults = FXCollections.observableArrayList();

		clTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		clAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		clAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		clId.setCellValueFactory(new PropertyValueFactory<>("id"));

		// Itera sobre todos os livros na lista de livros
		for (Livro book : listaLivro) {
			if (book.getTitulo().equalsIgnoreCase(txtTituloPesquisar.getText())) {
				searchResults.add(book);
				break;
			}
		}

		// Define a nova lista de resultados como o conteúdo da Tableview
		tbLivros.setItems(searchResults);

		// DB.closeConnection(conexao);
	}
	
	
	@FXML
	private void excluir() {
	    Livro livroexcluir = tbLivros.getSelectionModel().getSelectedItem();
	    LivroDAO livroDao = new LivroDAO();
	    livroDao.excluirLivro(livroexcluir);
	    tbLivros.getItems().remove(livroexcluir);
	    tbLivros.refresh();
	}


}
