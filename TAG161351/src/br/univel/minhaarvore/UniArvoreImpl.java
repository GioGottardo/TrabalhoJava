package br.univel.minhaarvore;

import br.univel.meustestes.Conta;

public class UniArvoreImpl<T> implements UniArvore<T> {
	
	class UniNode {
		}
	
	private T raiz;
	
	public UniArvoreImpl(T noRaiz){
		this.raiz = noRaiz;
	}
	
	public T getRaiz() {
		return raiz;
	}

}
