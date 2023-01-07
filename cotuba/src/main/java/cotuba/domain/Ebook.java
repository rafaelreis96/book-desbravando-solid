package cotuba.domain;

import java.nio.file.Path;
import java.util.List;

import cotuba.plugin.EbookSoParaLeitura;

public class Ebook implements EbookSoParaLeitura {

    private FormatoEbook formato;
    private Path arquivoDeSaida;
    private List<Capitulo> capitulos;
    
    public boolean isUltimoCapitulo(Capitulo capitulo) {
        return this.capitulos.get(this.capitulos.size() - 1).equals(capitulo);
    }

    @Override
    public FormatoEbook getFormato() {
        return formato;
    }

    public void setFormato(FormatoEbook formato) {
        this.formato = formato;
    }

    @Override
    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }

    public void setArquivoDeSaida(Path arquivoDeSaida) {
        this.arquivoDeSaida = arquivoDeSaida;
    }

    @Override
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulo(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

}
