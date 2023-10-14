package org.jrimum.domkee.banco;

public enum TipoSeguimento {

	PREFEITURA(1, "Prefeituras"),
	
	SANEAMENTO(2, "Saneamento"),
	
	ENERGIA_ELETRICA_E_GAS(3, "Energia Elétrica e Gás"),
	
	TELECOMUNICACOES(4, "Telecomunicações"),
	
	ORGAOS_GOVERNAMENTAIS(5, "Órgãos Governamentais"),
	
	CARNES_E_ASSEMELHADOS_OU_DEMAIS(6, "Carnês e Assemelhados ou demais"),
	
	MULTAS_DE_TRANSITO(7, "Multas de trânsito"),
	
	USO_EXCLUSIVO_BANCO(9, "Uso exclusivo do banco");
	
	
	private Integer codigo;
	private String descricao;
	
	/**
	 * @param codigo
	 * @param descricao
	 */
	private TipoSeguimento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the nome
	 */
	public String getDescricao() {
		return descricao;
	}	
	
	public static TipoSeguimento findByCodigo(Integer codigo) {
		TipoSeguimento tipoSeguimentoRetorno = null;
		
		TipoSeguimento[] tipoSeguimentoArray = TipoSeguimento.values();
		for (int i = 0; i < tipoSeguimentoArray.length; i++) {
			if (tipoSeguimentoArray[i].codigo.equals(codigo)) {
				tipoSeguimentoRetorno = tipoSeguimentoArray[i];
				break;
			}
				
		}
		
		return tipoSeguimentoRetorno;
	}
}
