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

import org.apache.log4j.Logger;

/**
 *
 * @author braully
 */
public class RemessaFacade extends ArquivoFacade {


	public static Logger logger = Logger.getLogger(ArquivoFacade.class);

    private boolean permiteQtdeMoeda = true;

    public boolean isPermiteQtdeMoeda() {
		return permiteQtdeMoeda;
	}

	public void setPermiteQtdeMoeda(boolean permiteQtdeMoeda) {
		this.permiteQtdeMoeda = permiteQtdeMoeda;
	}

	public RemessaFacade(TagLayout template) {
        this.template = template;

        if (template.equals(LayoutsSuportados.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA)) {
        	this.setPermiteQtdeMoeda(false);
        }

    }

    public CabecalhoArquivo addNovoCabecalho() {
        CabecalhoArquivo cabecalho = novoCabecalho("cabecalho");
        this.add(cabecalho);
        return cabecalho;
    }

    public CabecalhoArquivo addNovoCabecalhoLote() {
        CabecalhoArquivo cabecalho = novoCabecalho("cabecalhoLote");
        this.add(cabecalho);
        return cabecalho;
    }

    public CabecalhoArquivo novoCabecalho(String tipoCabecalho) {
        CabecalhoArquivo cabecalho = new CabecalhoArquivo(template.get(tipoCabecalho));
        return cabecalho;
    }

    public TituloArquivo addNovoDetalhe() {
        TituloArquivo titulo = this.novoTitulo("detalhe");
        this.add(titulo);
        return titulo;
    }

    public TituloArquivo addNovoDetalhe(String segmento) {
        TituloArquivo titulo = this.novoTitulo("detalheSegmento" + segmento);
        this.add(titulo);
        return titulo;
    }

    public TituloArquivo addNovoDetalheSegmentoA() {
        return addNovoDetalhe("A");
    }

    public TituloArquivo addNovoDetalheSegmentoB() {
        return addNovoDetalhe("B");
    }

    public TituloArquivo addNovoDetalheSegmentoJ() {
        return addNovoDetalhe("J");
    }

    public TituloArquivo addNovoDetalheSegmentoJ52() {
        return addNovoDetalhe("J52");
    }

    public TituloArquivo addNovoDetalheSegmentoP() {
        return addNovoDetalhe("P");
    }

    public TituloArquivo addNovoDetalheSegmentoQ() {
        return addNovoDetalhe("Q");
    }

    public TituloArquivo addNovoDetalheSegmentoR() {
        return addNovoDetalhe("R");
    }

    public TituloArquivo addNovoDetalheTransacao() {
        return addNovoDetalhe();
    }

    public TituloArquivo novoTitulo(String tipoTitulo) {
        TituloArquivo titulo = new TituloArquivo(template.get(tipoTitulo));
        return titulo;
    }

    public RodapeArquivo addNovoRodape() {
        RodapeArquivo rodape = this.novoRodape("rodape");
        this.add(rodape);
        return rodape;
    }

    public RodapeArquivo addNovoRodapeLote() {
        RodapeArquivo rodape = this.novoRodape("rodapeLote");
        this.add(rodape);
        return rodape;
    }

    public RodapeArquivo novoRodape(String tipoRodape) {
        RodapeArquivo rodape = new RodapeArquivo(template.get(tipoRodape));
        return rodape;
    }

    public RegistroArquivo addNovoRegistro(String tipoRegistro) {
        RegistroArquivo novoRegistro = novoRegistro(tipoRegistro);
        this.add(novoRegistro);
        return novoRegistro;
    }

    public RegistroArquivo novoRegistro(String tipoRegistro) {
        TagLayout layoutRegistro = template.get(tipoRegistro);
        if (layoutRegistro == null) {
            throw new IllegalArgumentException("Não existe registro do tipo " + tipoRegistro + " no layout " + template);
        }
        return new RegistroArquivo(layoutRegistro);
    }
}
