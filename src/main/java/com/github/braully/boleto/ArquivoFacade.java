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

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jrimum.utilix.FileUtil;

/**
 *
 * @author braully
 */
public class ArquivoFacade {

	public static Logger logger = LogManager.getLogger(ArquivoFacade.class);
	/*

	 */
	TagLayout template;

	public ArquivoFacade() {
	}

	public ArquivoFacade(TagLayout template) {
		this.template = template;
	}

	public List<String> renderLinhas() {
		List<String> linhas = new ArrayList<>();
		for (RegistroArquivo r : this.registros) {
			String render = r.render();
			linhas.add(render);
		}
		return linhas;
	}

	public void parse(List<String> linhas) {
		this.registros.clear();
		this.linhas.clear();
		if (linhas != null) {
			this.linhas.addAll(linhas);
		}
		List<RegistroArquivo> registrosLayout = new ArrayList<>();
		if (this.template != null && this.template.filhos != null) {
			for (TagLayout tag : this.template.filhos) {
				registrosLayout.add(new RegistroArquivo(tag));
			}
		}

		for (String linha : linhas) {
			if (linha == null) {
				continue;
			}
			// Remove new line character
			linha = linha.replace("\r", "").replace("\n", "");
			RegistroArquivo regLido = null;
			for (RegistroArquivo reg : registrosLayout) {
				RegistroArquivo clone = reg.clone();
				if (clone.checkIds(linha)) {
					regLido = clone;
					// System.out.println(reg.getDescricaoLayout());
					regLido.read(linha);
				}
			}
			if (regLido != null) {
				this.registros.add(regLido);
			} else {
				throw new IllegalStateException(
						"Linha não reconhecida no layout linha=" + linha + " layout=" + this.template);
			}
		}
	}

//TODO: Melhorar isso
	public String render() {
		StringBuilder sb = new StringBuilder();
		List<String> linhas = this.renderLinhas();
		for (String linha : linhas) {
			sb.append(linha);
			sb.append(FileUtil.NEW_LINE);
		}
		return sb.toString();

	}

	/*

	 */
	List<RegistroArquivo> registros = new ArrayList<>();
	List<String> linhas = new ArrayList<>();

	public ArquivoFacade add(RegistroArquivo reg) {
		registros.add(reg);
		return this;
	}

	public RegistroArquivo get(String str) {
		return this.get(template.get(str));
	}

	public RegistroArquivo get(TagLayout tiporegistro) {
		// Melhorar isso, indexar via Map
		for (RegistroArquivo reg : registros) {
			if (reg.layoutRegistro == tiporegistro) {
				return reg;
			}
		}
		return null;
	}

	public List<RegistroArquivo> gets(String str) {
		return this.gets(template.get(str));
	}

	public List<RegistroArquivo> gets(TagLayout tiporegistro) {
		List<RegistroArquivo> regs = new ArrayList<>();
		// Melhorar isso, indexar via Map
		for (RegistroArquivo reg : registros) {
			if (reg.getName().equalsIgnoreCase(tiporegistro.nome)) {
				regs.add(reg);
			}
		}
		return regs;
	}
}
