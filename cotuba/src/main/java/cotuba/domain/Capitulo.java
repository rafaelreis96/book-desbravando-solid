package cotuba.domain;

import cotuba.plugin.CapituloSoParaLeitura;

public class Capitulo implements CapituloSoParaLeitura {

    private String titulo;
    private String conteudoHTML;

    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getConteudoHTML() {
        return conteudoHTML;
    }

    public void setConsteudoHTML(String consteudoHTML) {
        this.conteudoHTML = consteudoHTML;
    }

}
