package br.edu.ifpb.domain;

import java.util.Collections;
import java.util.List;

public class Banda {
    private int id;
    private String localOrigem;
    private String nomeFantasia;
    private List<Integrante> integrantes;

    public Banda( String localOrigem, String nomeFantasia, List<Integrante> integrantes) {

        this.localOrigem = localOrigem;
        this.nomeFantasia = nomeFantasia;
        this.integrantes = integrantes;
    }

    private Banda(int id, String localOrigem, String nomeFantasia, List<Integrante> integrantes) {
        this.id = id;
        this.localOrigem = localOrigem;
        this.nomeFantasia = nomeFantasia;
        this.integrantes = integrantes;
    }

    public Banda() {

    }

    public static Banda off(int id, String localOrigem, String nomeFantasia, List<Integrante> integrantes){
        return new Banda(id,localOrigem,nomeFantasia,integrantes);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<Integrante> getIntegrantes() {
        return Collections.unmodifiableList(this.integrantes);
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }
}
