package cotuba.pdf;

import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;

import cotuba.plugin.GeradorEbook;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

@Component
public class GeradorPDF implements GeradorEbook {

	@Override
	public void gera(Ebook ebook) {
		try (var writer = new PdfWriter(Files.newOutputStream(ebook.arquivoDeSaida()));
				var pdf = new PdfDocument(writer);
				var pdfDocument = new Document(pdf)) {

			for (Capitulo capitulo : ebook.capitulos()) {

				String html = capitulo.conteudoHTML();

				List<IElement> convertToElements = HtmlConverter.convertToElements(html);
				for (IElement element : convertToElements) {
					pdfDocument.add((IBlockElement) element);
				}

				if (!ebook.ultimoCapitulo(capitulo)) {
					// TODO: não adicionar página depois do último capítulo
					pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				}

			}

		} catch (Exception ex) {
			throw new IllegalStateException("Erro ao criar arquivo PDF: " + ebook.arquivoDeSaida().toAbsolutePath(),
					ex);
		}
	}

	@Override
	public boolean accept(FormatoEbook formato) {
		return FormatoEbook.PDF.equals(formato);
	}
}
