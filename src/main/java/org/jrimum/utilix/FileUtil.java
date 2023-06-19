/*
 * Copyright 2008 JRimum Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * Created at: 30/03/2008 - 18:17:54
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 *
 * Criado em: 30/03/2008 - 18:17:54
 *
 */
package org.jrimum.utilix;

import static org.jrimum.utilix.ObjectUtil.isNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Descrição:
 *
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class FileUtil {

	// TODO Criar Metodo que recebe um arquivo e coisas para verificar nele, como:
	// (isVazio,Numero de linhas, etc)
	protected static final Logger LOG = LogManager.getLogger(FileUtil.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1227314921804015225L;

	public static final int EOF = -1;

	public static final int CARRIAGE_RETURN = 1;

	public static final int NEXT_LINE = 2;

	public static final String NEW_LINE = "\r\n";

	/**
	 * Retorna o conteúdo de um arquivo em um array de bytes.
	 *
	 * @param file
	 * @return Array de bytes com o conteúdo de um File
	 * @throws IOException
	 */
	public static byte[] bytesFromFile(File file) throws IOException {

		byte[] bytes = null;

		if (isNotNull(file)) {

			// medida do arquivo
			long length = file.length();

			// Nao se pode criar um array usando o tipo long.
			// Tem que ser int.
			// Antes de converter para o tipo int cheque
			// para assegurar que file.lenth não é maior que Integer.MAX_VALUE.
			if (length <= Integer.MAX_VALUE) {

				InputStream is = new FileInputStream(file);

				// para os dados
				bytes = new byte[(int) length];

				// leitura dos bytes
				int offset = 0;
				int numRead = 0;

				while ((offset < bytes.length) && ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) {
					offset += numRead;
				}

				// Assegurando que todos os dados foram lidos
				if (offset < bytes.length) {
					throw new IOException("Não foi possível ler completamente o arquivo. [" + file.getName() + "]");
				}

				// Feche o input stream
				is.close();

			} else {
				throw new IOException("O arquivo é muito grande para esta transformação.");
			}

		}

		return bytes;
	}

	/**
	 * Transforma um array de bytes em um arquivo.
	 *
	 * @param pathName
	 * @param bytes
	 * @return Objeto File com o conteúdo sendo o dos bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File bytes2File(String pathName, byte[] bytes) throws FileNotFoundException, IOException {

		File f = null;

		if (isNotNull(pathName, "pathName") && isNotNull(bytes, "bytes")) {

			f = new File(pathName);

			OutputStream out = new FileOutputStream(f);

			out.write(bytes);
			out.flush();
			out.close();
		}

		return f;
	}

	/**
	 * <p>
	 * Transforma um array de bytes em um <code>ByteArrayOutputStream</code>.
	 * </p>
	 *
	 * @param bytes
	 * @return ByteArrayOutputStream ou null
	 * @throws IOException
	 *
	 * @since
	 */
	public static ByteArrayOutputStream bytes2Stream(byte[] bytes) throws IOException {

		ByteArrayOutputStream byteOut = null;

		if (isNotNull(bytes, "bytes")) {

			byteOut = new ByteArrayOutputStream();

			byteOut.write(bytes);
		}

		return byteOut;
	}

	public static String readLine(File file, int lengthOfBlock, int lineOrdinalNumber) {

		long position = 0;

		FileChannel fc = null;
		ByteBuffer bybff = null;

		String line = null;

		if (lengthOfBlock > 0) {
			if (lineOrdinalNumber > 0) {

				fc = getReadFileChannel(file);

				try {

					if (fc.size() > 0) {

						lengthOfBlock += NEXT_LINE;

						bybff = ByteBuffer.allocate(lengthOfBlock - CARRIAGE_RETURN);

						position = ((lineOrdinalNumber - 1) * lengthOfBlock);

						fc.position(position);

						fc.read(bybff);

						line = new String(bybff.array());

						fc.close();

					} else {
						throw new IllegalArgumentException("file : [" + file + "] is empty!");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				throw new IllegalArgumentException("lineOrdinalNumber : [" + lineOrdinalNumber + "] deve ser > 0!");
			}
		} else {
			throw new IllegalArgumentException("lengthOfBlock : [" + lengthOfBlock + "] deve ser > 0!");
		}

		return line;
	}

	public static List<String> readLines(File file, int lengthOfBlock) {

		FileChannel fc = null;
		ByteBuffer bybff = null;

		List<String> blocks = null;

		if (lengthOfBlock > 0) {

			fc = getReadFileChannel(file);

			try {

				if (fc.size() > 0) {

					lengthOfBlock += CARRIAGE_RETURN;

					bybff = ByteBuffer.allocate(lengthOfBlock - CARRIAGE_RETURN);

					blocks = new ArrayList<String>(getNumberOfLines(fc, lengthOfBlock));

					while (fc.read(bybff) != EOF) {

						blocks.add(new String(bybff.array()));

						fc.position(fc.position() + NEXT_LINE);

						bybff.clear();
					}

					fc.close();

				} else {
					throw new IllegalArgumentException("file : [" + file + "] está vazio!");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("lengthOfBlock : [" + lengthOfBlock + "] deve ser > 0!");
		}

		return blocks;
	}

	public static void writeLines(File file, List<String> lines) {
		FileChannel fc = null;
		ByteBuffer bybff = null;
		ByteBuffer[] bybffArray = null;
		String line = null;
		if (isNotNull(lines) && !lines.isEmpty()) {
			bybffArray = new ByteBuffer[lines.size()];
			for (int i = 0; i < lines.size(); i++) {
				line = lines.get(i);
				bybff = ByteBuffer.allocate(line.length() + NEXT_LINE);
				line += NEW_LINE;
				bybff.put(line.getBytes());
				bybff.rewind();
				bybffArray[i] = bybff;
			}
			fc = getWriteFileChannel(file);
			try {
				fc.write(bybffArray);
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isEmpty(File file) {

		boolean is = true;

		if (isNotNull(file)) {
			is = (file.length() > 0);
		} else {
			throw new IllegalArgumentException("file : [" + file + "]!");
		}

		return is;
	}

	public static int getNumberOfLines(File file, int lengthOfBlock) {

		int size = 0;

		if (lengthOfBlock > 0) {

			size = getNumberOfLines(getReadFileChannel(file), lengthOfBlock);

		} else {
			throw new IllegalArgumentException("lengthOfBlock : [" + lengthOfBlock + "] deve ser > 0!");
		}

		return size;
	}

	public static int getNumberOfLines(FileChannel fileChannel, int lengthOfBlock) {

		int size = 0;

		if (isNotNull(fileChannel)) {
			if (lengthOfBlock > 0) {

				try {

					if (fileChannel.size() > 0) {

						lengthOfBlock += CARRIAGE_RETURN;

						size = (int) (fileChannel.size() / lengthOfBlock);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				throw new IllegalArgumentException("lengthOfBlock : [" + lengthOfBlock + "] deve ser > 0!");
			}
		} else {
			throw new IllegalArgumentException("fileChannel : [" + fileChannel + "]!");
		}

		return size;
	}

	public static void copyTo(File fileIn, File fileOut) {

		FileChannel fcin = getReadFileChannel(fileIn);

		FileChannel fcout = getWriteFileChannel(fileOut);

		try {

			fcin.transferTo(0, fcin.size(), fcout);

			fcin.close();
			fcout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileChannel getReadFileChannel(File file) {

		FileInputStream fis = null;
		FileChannel fc = null;

		if (!isEmpty(file)) {

			try {

				fis = new FileInputStream(file);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			fc = fis.getChannel();
		}

		return fc;
	}

	public static FileChannel getWriteFileChannel(File file) {

		FileOutputStream fos = null;
		FileChannel fc = null;

		if (!isEmpty(file)) {

			try {

				fos = new FileOutputStream(file);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			fc = fos.getChannel();
		}

		return fc;
	}

	public static List<String> readFile(String pathName) {

		if (isNotNull(pathName)) {

			List<String> lines = new ArrayList<String>();

			try {
				File arq = new File(pathName);

				BufferedReader reader = new BufferedReader(new FileReader(arq));

				String s;

				do {
					s = reader.readLine();
					if (isNotNull(s)) {
						lines.add(s);
					}
				} while (isNotNull(s));

				reader.close();

				return lines;

			} catch (FileNotFoundException e) {
				LOG.error(" RECEBER PROCESSAMENTO " + "String pathNomeArquivo: " + pathName, e);
			} catch (IOException e) {
				LOG.error(" RECEBER PROCESSAMENTO " + "String pathNomeArquivo: " + pathName, e);
			}
		}

		return null;
	}

	public static List<String> readFile(File file) {

		List<String> lines = null;

		if (!isEmpty(file)) {

			try {

				lines = new ArrayList<String>();

				BufferedReader reader = new BufferedReader(new FileReader(file));

				String s;

				do {
					s = reader.readLine();
					if (isNotNull(s)) {
						lines.add(s);
					}
				} while (isNotNull(s));

				reader.close();

				return lines;

			} catch (FileNotFoundException e) {
				LOG.error(" RECEBER PROCESSAMENTO ", e);
			} catch (IOException e) {
				LOG.error(" RECEBER PROCESSAMENTO ", e);
			}
		}

		return lines;
	}

	public static void markAs(String pathName, String tag) {

		if (isNotNull(pathName) & isNotNull(tag)) {

			File file = new File(pathName);
			File newFile = new File(pathName + tag);

			file.renameTo(newFile);

		}
	}

	public static boolean renameTo(String path, String name, String newName) {

		if (isNotNull(path) & isNotNull(name) & isNotNull(newName)) {

			File file = new File(path + "/" + name);
			File newFile = new File(path + "/" + newName);

			file.renameTo(newFile);
			return true;
		}

		return false;
	}

	public static void createTextFile(String pathName, String content) {

		if (LOG.isTraceEnabled()) {
			LOG.trace("Creating file...");
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("pathName: " + pathName);
			LOG.debug("content: " + content);
		}

		List<String> line = new ArrayList<String>(1);
		line.add(content);

		writeLines(new File(pathName), line);
	}

	public static void createTextFile(String pathName, List<String> content) {

		if (LOG.isTraceEnabled()) {
			LOG.trace("Creating file...");
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("pathName: " + pathName);
			LOG.debug("content: " + content);
		}

		writeLines(new File(pathName), content);
	}

	public static InputStream streamFromClasspath(String classpath) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(classpath);
	}
}
