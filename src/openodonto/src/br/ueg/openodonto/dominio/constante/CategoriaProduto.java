package br.ueg.openodonto.dominio.constante;

public enum CategoriaProduto {

	SERVICO("Servi�o"),PRODUTO("Produto");
	
	private String descricao;
	
	private CategoriaProduto(String descricao) {
		this.descricao = descricao;		
	}
	
	
	public String getDescricao(){
		return descricao;
	}
	
	public long getCodigo(){
		return ordinal();
	}
	
	public String toString(){
		return this.descricao;
	}
	
}
