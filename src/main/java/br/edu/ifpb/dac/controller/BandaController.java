package br.edu.ifpb.dac.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.entidade.Banda;
import br.edu.ifpb.dac.servico.BandaServico;

@Named
@SessionScoped
public class BandaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Banda banda;
	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	private BandaServico bandaServico;
	
	public BandaController() {
		banda = new Banda();
		bandaServico = new BandaServico();
	}

	public String salvarBanda() {
		bandaServico.Add(banda);
		banda = new Banda();
		return "listBanda";
	}
}
