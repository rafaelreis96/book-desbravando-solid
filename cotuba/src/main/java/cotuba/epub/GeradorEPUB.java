package cotuba.epub;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Component;

import cotuba.application.GeradorEbook;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

@Component
public class GeradorEPUB implements GeradorEbook {

    @Override
	public void gera(Ebook ebook) {
        var epub = new Book();
        
        for(Capitulo capitulo: ebook.getCapitulos()) {
            String html = capitulo.getConteudoHTML();
            epub.addSection(capitulo.getTitulo(), new Resource(html.getBytes(), MediatypeService.XHTML));
        }
        
        var epubWriter = new EpubWriter();

        try {
            epubWriter.write(epub, Files.newOutputStream(ebook.getArquivoDeSaida()));
        } catch (IOException ex) {
            throw new IllegalStateException(
                    "Erro ao criar arquivo EPUB: " + ebook.getArquivoDeSaida().toAbsolutePath(), ex);
        }
    }

	@Override
	public boolean accept(FormatoEbook formato) {
		return FormatoEbook.EPUB.equals(formato);
	}

}
