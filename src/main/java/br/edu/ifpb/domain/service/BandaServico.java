package br.edu.ifpb.domain.service;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Integrante;

import java.util.List;

public class BandaServico {
    private static final long serialVersionUID = 1L;
   Banda BandasJDBC;

    public BandaServico(Banda bandasJDBC) {
        this.BandasJDBC = new BandasJDBC;
    }
    public void nova(Banda banda) {
        Banda retorno = this.BandasJDBC.localizarBandasJDBComId(banda.getId());
        if (null == retorno || Banda.fake().equals(retorno)) {
            BandasJDBC.nova(banda);

        } else {
            BandasJDBC.atualizar(banda);
        }

    }

    @Override
    public List<Banda> todas() {
        // TODO Auto-generated method stub
        return this.BandasJDBC.todas();
    }

    @Override
    public void excluir(Banda banda) {
        // TODO Auto-generated method stub
        this.BandasJDBC.excluir(banda);
    }

    @Override
    public void excluirIntegrantee(Integrante integrante) {
        this.BandasJDBC.excluirIntegrante(integrante);
    }

    @Override
    public void atualizar(Banda banda) {
        // TODO Auto-generated method stub
        this.bandasJDBC.atualizar(banda);
        //this.PessoasJDBC.atualizar(pessoa);

    }

    @Override
    public Pessoa localizarPessoaComId(long id) {
        // TODO Auto-generated method stub
        return PessoasJDBC.localizarPessoaComId(id);

    }

    @Override
    public List<Dependente> todosOsDepentendes() {
        return this.PessoasJDBC.todosOsDepentendes();
    }

    @Override
    public Dependente localizarDependenteComId(long uuid) {
        // TODO Auto-generated method stub
        return this.PessoasJDBC.localizarDependenteComId(uuid);
    }

    @Override
    public void novo(Dependente dependente) {
        this.PessoasJDBC.novo(dependente);

    }

    @Override
    public List<Pessoa> localizarPessoaComCPF(String cpf) {
        List<Pessoa> ps = this.PessoasJDBC.localizarPessoaComCPF(cpf);
        for (Pessoa p : ps) {
        }
        return ps;
    }
}
