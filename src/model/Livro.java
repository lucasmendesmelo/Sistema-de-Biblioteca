package model;

public class Livro {
	private Integer id;
	private String titulo;
	private String autor;
	private Integer ano;

	public Livro() {
	}
	
	public Livro(String titulo, String autor, Integer ano) {
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
	}
	
	public Livro(Integer id,String titulo, String autor, Integer ano) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
