package br.com.cognitio.estatisticas;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ContagemDePalavras implements Iterable<ContagemDePalavras.Contagem>{
	
	Map<String, Integer> map = new TreeMap<>();
	
	static record Contagem(String palavra, int ocorrencias) {
		
	}
	
	void adicionaPalavra(String palavra) {
		Integer contagem = map.get(palavra);
		
		if(contagem != null) {
			contagem++;
		} else {
			contagem = 1;
		}
		
		map.put(palavra, contagem);
	}
	
	public Iterator<Contagem> iterator() {
		Iterator<Map.Entry<String, Integer>> iterator = this.map.entrySet().iterator();
		
		return new Iterator<Contagem>() {

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Contagem next() {
				Map.Entry<String, Integer> entry = iterator.next();
				String palavra = entry.getKey();
				int ocorrencias = entry.getValue();
				
				return new Contagem(palavra, ocorrencias);
			}
			
		};
	}
	
	

}
