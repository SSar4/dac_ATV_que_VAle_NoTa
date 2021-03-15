package br.edu.ifpb.dac.identidade;


import java.time.LocalDate;

public class Integrante {
	private int id;
	private String nome;
	private LocalDate dataDeNascimento;
	private Cpf cpf = new Cpf("");
	
	public Integrante() {
		
	}
	
	private Integrante(int id, String nome, LocalDate dataDeNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public Integrante(String nome, LocalDate dataDeNascimento) {
		super();
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}

	public static Integrante of(int id, String nome, LocalDate dataDeNascimento) {
		return new Integrante(id,nome,dataDeNascimento);
	}

	public static Integrante fake(){
		return new Integrante("fake");
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}


	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}
	

}
