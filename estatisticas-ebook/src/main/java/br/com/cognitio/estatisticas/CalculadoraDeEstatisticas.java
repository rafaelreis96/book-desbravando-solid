package br.com.cognitio.estatisticas;

import java.text.Normalizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

	public String aposRenderizacao(String html) {
		return html;
	}

	@Override
	public void aposGeracao(Ebook ebook) {
		
		var contagemDepalavras = new ContagemDePalavras();

		
		for(Capitulo capitulo : ebook.capitulos()) {
			String html = capitulo.conteudoHTML();
			
			Document doc = Jsoup.parse(html);
			
			String textoDoCapitulo = doc.body().text();
			
			String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}}", "");
			
			String decomposta = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD);
			
			String textoSemAcentos = decomposta.replaceAll("[^\\p{ASCII}]", "");
			
			String[] palavras = textoSemAcentos.split("\\s+");

			
			
			for(String palavra: palavras) {
				
				String emMaiusculas = palavra.toUpperCase();

				contagemDepalavras.adicionaPalavra(emMaiusculas);
			}
			
		}
		
		for(ContagemDePalavras.Contagem contagem : contagemDepalavras) {
			System.out.println(contagem.palavra() + ": " + contagem.ocorrencias());
		}
		
	}
	
}