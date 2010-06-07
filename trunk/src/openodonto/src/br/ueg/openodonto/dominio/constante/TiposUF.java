package br.ueg.openodonto.dominio.constante;



/**
 * @author Vinicius
 *
 */
public enum TiposUF {

	AC("Acre"),
	AL("Alagoas"),
	AM("Amazonas"),
	AP("Amap�"),
	BA("Bahia"),
	CE("Ceara"),
	DF("Distro Federeal"),
	ES("Esp�rito Santo"),
	GO("Goi�s"),
	MA("Maranhao"),
	MG("Minas Gerais"),
	MS("Mato Grosso do Sul"),
	MT("Mato Grosso"),
	PA("Par�"),
	PB("Paraiba"),
	PE("Pernambuco"),
	PI("Piau�"),
	PR("Parana"),
	RJ("Rio de Janeiro"),
	RN("Rio Grande do Norte"),
	RO("Rondonia"),
	RR("Roraima"),
	RS("Rio Grande do Sul"),
	SC("Santa Catarina"),
	SE("Segipe"),
	SP("S�o Paulo"),
	TO("Tocantins");
	
	private String descricao;
	
	private TiposUF(String descricao){
		this.descricao = descricao;			
	}
	
	public String toString(){
		return this.descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public static TiposUF parse(int index){
		TiposUF[] values = TiposUF.values();
		if(index >= 0 || index < values.length){
			return values[index];
		}else{
			return null;
		}
			
	}
	
	public long getCodigo(){
		return ordinal();
	}
	
}
