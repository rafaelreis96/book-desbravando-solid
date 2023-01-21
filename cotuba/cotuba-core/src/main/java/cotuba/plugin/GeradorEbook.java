package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

public interface GeradorEbook {

	void gera(Ebook ebook);
	
	boolean accept(FormatoEbook formato);
		
	static GeradorEbook cria(FormatoEbook formato) {
		for(GeradorEbook gerador : ServiceLoader.load(GeradorEbook.class)) {
			if(gerador.accept(formato)) {
				return gerador;
			}
		}
		
		throw new IllegalArgumentException("Formato do Ebook Inválido: " + formato);
	}
	
}
