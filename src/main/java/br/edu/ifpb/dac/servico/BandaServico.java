package br.edu.ifpb.dac.servico;

import br.edu.ifpb.dac.entidade.Banda;
import br.edu.ifpb.dac.persistencia.BandaJDBC;

public class BandaServico {

	private BandaJDBC bandas;
	
	public BandaServico() {
		bandas = new BandaJDBC();
	}

	public void Add(Banda b) {
		bandas.addBanda(b);
	}
}
