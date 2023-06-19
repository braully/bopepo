/*
 * Copyright 2019 Projeto JRimum.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.braully.boleto;

import static com.github.braully.boleto.TagLayout.TagCreator.*;

import java.util.ArrayList;

import org.jrimum.texgit.FixedField;

/**
 *
 * @author braully
 */
public class TituloArquivo extends RegistroArquivo {

	public TituloArquivo(TagLayout get) {
		super(get);
	}

	public TituloArquivo(RegistroArquivo reg) {
		this.name = reg.getName();
		this.description = reg.getDescricaoLayout();
		this.declaredInnerRecords = reg.getDeclaredInnerRecords();
		this.idType = reg.getIdType();
		this.extraIds = reg.extraIds;
		this.fields = (ArrayList<FixedField<?>>) reg.getFields();
		this.layoutRegistro = reg.layoutRegistro;
	}

	/*
	 * remessa.addTitulo().valor("").vencimento("")
	 * .numeroDocumento("").nossoNumero("") .dataEmissao("").carteira("")
	 * .sacado("Sacado da Silva Sauro").sacadoCpf("01234567891") .sacadoEndereco("")
	 * .instrucao("");
	 */
	public TituloArquivo sacado(String nome, String cpf) {
		setValue(fsacadoNome().nome, nome).setValue(fsacadoCpf().nome, cpf);
		return this;
	}

	public String sacadoCpf() {
		return getValue(fsacadoCpf().nome);
	}

	public TituloArquivo valor(Object string) {
		return (TituloArquivo) setValue(fvalor().nome, string);
	}

	public String valor() {
		return getValue(fvalor().nome);
	}

	public TituloArquivo valorDesconto(Object string) {
		return (TituloArquivo) setValue(fvalorDesconto().nome, string);
	}

	public String valorDesconto() {
		return getValue(fvalorDesconto().nome);
	}

	public TituloArquivo valorAcrescimo(Object string) {
		return (TituloArquivo) setValue(fvalorAcrescimo().nome, string);
	}

	public TituloArquivo dataAcrescimo(Object string) {
		return (TituloArquivo) setValue(fdataAcrescimo().nome, string);
	}

	public TituloArquivo dataPagamento(Object string) {
		return (TituloArquivo) setValue(fdataPagamento().nome, string);
	}

	public TituloArquivo dataDesconto(Object string) {
		return (TituloArquivo) setValue(fdataDesconto().nome, string);
	}

	public TituloArquivo favorecidoCodigoBanco(Object favorecidoCodigoBanco) {
		return (TituloArquivo) setValue(favorecidoCodigoBanco);
	}

	public TituloArquivo formaDeTransferencia(Object formaDeTransferencia) {
		return (TituloArquivo) setValue(formaDeTransferencia);
	}



	public TituloArquivo favorecidoAgencia(String favorecidoAgencia) {

    	if (favorecidoAgencia != null && favorecidoAgencia.length() == 4) {
    		//caso o usuario não tenha informado o digito verificador, assumir que é 0
    		favorecidoAgencia = favorecidoAgencia + "0";
    	}

		return (TituloArquivo) setValue(favorecidoAgencia);
	}

	/**
     * Tipo de Inscrição: '0' = Isento / Não Informado '1' = CPF '2' = CGC /
     * CNPJ '3' = PIS / PASEP '9' = Outros
     */
	public TituloArquivo tipoInscricao(Object tipoInsricao) {

		return (TituloArquivo) setValue(tipoInsricao);
	}

	/**
     * Tipo de Inscrição: '0' = Isento / Não Informado '1' = CPF '2' = CGC /
     * CNPJ '3' = PIS / PASEP '9' = Outros
     */
	public TituloArquivo favorecidoTipoInscricao(Object favorecidoTipoInscricao) {

		return (TituloArquivo) setValue(favorecidoTipoInscricao);
	}

	public TituloArquivo favorecidoConta(Object favorecidoConta) {
		return (TituloArquivo) setValue(favorecidoConta);
	}

	public TituloArquivo favorecidoNome(Object favorecidoNome) {
		return (TituloArquivo) setValue(favorecidoNome);
	}


	public TituloArquivo favorecidoCPFCNPJ(Object favorecidoInscricao) {

		return (TituloArquivo) setValue(favorecidoInscricao);
	}

	public String valorAcrescimo() {
		return getValue(fvalorAcrescimo().nome);
	}

	public TituloArquivo vencimento(String string) {
		return (TituloArquivo) setValue(string);
	}

	public String vencimento() {
		return getValue("vencimento");
	}

	public TituloArquivo numeroDocumento(Object string) {
		return (TituloArquivo) setValue(string);
	}

	public String numeroDocumento() {
		return getValue(fnumeroDocumento().nome);
	}

	public TituloArquivo codigoBarras(String codigoBarras) {
		return (TituloArquivo) setVal(fcodigoBarras().nome, codigoBarras);
	}

	public TituloArquivo nossoNumero(Object string) {
		return (TituloArquivo) setValue(fnossoNumero().nome, string);
	}

	/**
	 * @see com.​github.​braully.​boleto.​TagLayout.​TagCreator.fmovimentoCodigo()
	 */
	public TituloArquivo movimentoCodigo(Object string) {
		return (TituloArquivo) setValue(fmovimentoCodigo().nome, string);
	}

	public String nossoNumero() {
		return getValue("nossoNumero");
	}

	public TituloArquivo dataVencimento(Object vencimento) {
		return (TituloArquivo) setValue(vencimento);
	}

	public TituloArquivo dataGeracao(Object emissao) {
		return (TituloArquivo) setValue(emissao);
	}



	public TituloArquivo dataEmissao(Object emissao) {
		return (TituloArquivo) setValue(emissao);
	}

	public String dataOcorrencia(Object emissao) {
		return getValue(fdataOcorrencia().nome);
	}

	public Number valorOcorrencia(Object string) {
		return getValue(fvalorOcorrencia().nome);
	}

	public String ocorrencias(Object string) {
		return getValue(focorrencias().nome);
	}

	public String segmento() {
		return getValue(fsegmento().nome);
	}

	public TituloArquivo carteira(String string) {
		return (TituloArquivo) setValue(string);
	}

	public TituloArquivo sacado(String string) {
		return (TituloArquivo) setValue(string);
	}

	public TituloArquivo sacadoCpf(String string) {
		return (TituloArquivo) setValue(string);
	}

	public TituloArquivo sacadoEndereco(String string) {
		return (TituloArquivo) setValue(string);
	}

	/**
	 *
	 * @param endereco
	 * @param bairro
	 * @param cep
	 * @param cidade
	 * @param uf
	 * @return
	 */
	public TituloArquivo sacadoEndereco(String endereco, String bairro, String cep, String cidade, String uf) {
		return (TituloArquivo) setValue(fendereco().nome, endereco).setValue(fbairro().nome, bairro)
				.setValue(fcep().nome, cep).setValue(fcidade().nome, cidade).setValue(fuf().nome, uf);
	}

	public TituloArquivo instrucao(String string) {
		return (TituloArquivo) setValue(string);
	}

	public String valorPagamento() {
		return getValue(fvalorPagamento().nome);
	}

	public String valorLiquido() {
		return getValue(fvalorLiquido().nome);
	}

	public String dataOcorrencia() {
		return getValue(fdataOcorrencia().nome);
	}

//    public String valorDesconto() {
//        return getValue(fvalorDesconto().nome);
//    }
	public String rejeicoes() {
		return getValue(frejeicoes().nome);
	}

	public String valorTarifaCustas() {
		return getValue(fvalorTarifaCustas().nome);
	}
}
