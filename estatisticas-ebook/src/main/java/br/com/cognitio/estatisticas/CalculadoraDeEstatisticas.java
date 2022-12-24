package br.com.cognitio.estatisticas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;

public class CalculadoraDeEstatisticas implements Plugin {

	public String aposRenderizacao(String html) {
		return html;
	}

	public void aposGeracao(Ebook ebook) {
		for(Capitulo capitulo : ebook.getCapitulos()) {
			String html = capitulo.getConteudoHTML();
			
			Document doc = Jsoup.parse(html);
			
			String textoDoCapitulo = doc.body().text();
			
			String[] palavras = textoDoCapitulo.split("\\S+");
			
			for(String palavra: palavras) {
				System.out.println(palavra);
			}
		}
		
	}

}