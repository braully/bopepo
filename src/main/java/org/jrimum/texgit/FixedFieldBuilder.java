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
 * Created at: 26/07/2008 - 12:44:41
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
 * Criado em: 26/07/2008 - 12:44:41
 * 
 */
package org.jrimum.texgit;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.countMatches;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.jrimum.utilix.Objects.isNotNull;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jrimum.texgit.TexgitException;
import org.jrimum.utilix.Dates;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
class FixedFieldBuilder {

	private final static String BASE_DECIMAL_FORMAT = "0.";
	
	static FixedField<?> build(MetaField metaField) {

		FixedField<?> fixedField = null;

		try {

			fixedField = getInstance(metaField);

		} catch (ParseException e) {
			throw new TexgitException("Field: " + metaField.getName(), e);
		}

		return fixedField;
	}

	private static FixedField<?> getInstance(MetaField metaField)
			throws ParseException {

		FixedField<?> fField = null;

		Format formatter = getFormater(metaField);

		switch (metaField.getType()) {
		
		case CHARACTER:
			FixedField<Character> fCHR = new FixedField<Character>();
			if (isNotBlank(metaField.getValue()))
				if(metaField.getValue().length() == 1)
					fCHR.setValue(metaField.getValue().charAt(0));
				else
					throw new IllegalArgumentException("Tipo character deve ter apenas 1!");
			else
				fCHR.setValue(' ');
			fField = fCHR;
			break;
		case STRING:
			FixedField<String> fSTR = new FixedField<String>();
			if (isNotBlank(metaField.getValue()))
				fSTR.setValue(metaField.getValue());
			else
				fSTR.setValue(EMPTY);
			fField = fSTR;
			break;
		case INTEGER:
			FixedField<Integer> fINT = new FixedField<Integer>();
			if (isNotBlank(metaField.getValue()))
				fINT.setValue(Integer.parseInt(metaField.getValue()));
			else
				fINT.setValue(Integer.valueOf(0));
			fField = fINT;
			break;
		case LONG:
			FixedField<Long> fLNG = new FixedField<Long>();
			if (isNotBlank(metaField.getValue()))
				fLNG.setValue(Long.parseLong(metaField.getValue()));
			else
				fLNG.setValue(Long.valueOf(0));
			fField = fLNG;
			break;
		case FLOAT:
			FixedField<Float> fFLT = new FixedField<Float>();
			if (isNotBlank(metaField.getValue()))
				fFLT.setValue(Float.parseFloat(metaField.getValue()));
			else
				fFLT.setValue(Float.valueOf(0));
			fField = fFLT;
			break;
		case DOUBLE:
			FixedField<Double> fDBE = new FixedField<Double>();
			if (isNotBlank(metaField.getValue()))
				fDBE.setValue(Double.parseDouble(metaField.getValue()));
			else
				fDBE.setValue(Double.valueOf(0));
			fField = fDBE;
			break;
		case BIGDECIMAL:
			FixedField<BigDecimal> fBDL = new FixedField<BigDecimal>();
			if (isNotBlank(metaField.getValue()))
				fBDL.setValue(new BigDecimal(DecimalFormat.class
						.cast(formatter).parse(metaField.getValue())
						.doubleValue()));
			else
				fBDL.setValue(BigDecimal.ZERO);
			fField = fBDL;
			break;
		case DATE:
			FixedField<Date> fDTE = new FixedField<Date>();
			if (isNotBlank(metaField.getValue())){
				
				fDTE.setValue(DateFormat.class.cast(formatter).parse(
						metaField.getValue()));
			}
			else{
				
				fDTE.setValue(Dates.invalidDate());
			}
			fField = fDTE;
			break;
		}
		
		fField.setName(metaField.getName());
		fField.setFixedLength(metaField.getLength());
		fField.setFiller(getFiller(metaField));
		fField.setBlankAccepted(metaField.isBlankAccepted());
		fField.setTruncate(metaField.isTruncate());
		
		if(isNotNull(formatter))
			fField.setFormatter(formatter);

		return fField;
	}

	private static IFiller getFiller(MetaField metaField) {

		IFiller filler = null;

		if (isNotNull(metaField.getFiller())) {

			org.jrimum.texgit.Filler<String> filr = new org.jrimum.texgit.Filler<String>();
			filr.setPadding(metaField.getFiller().getPadding());
			filr.setSideToFill(Side.valueOf(metaField.getFiller()
					.getSideToFill().name()));
			filler = filr;

		} else {
			filler = Fillers.valueOf(metaField.getPadding().name());
		}

		return filler;
	}

	private static Format getFormater(MetaField metaField) {

		Format formatter = null;

		if (isNotNull(metaField.getFormatter())) {

			formatter = buildFormat(metaField.getFormatter().getFormat(),
					metaField.getFormatter().getType());

		} else {
			if(isNotNull(metaField.getFormat())){
			
				EnumFormats format = metaField.getFormat(); 
				
				EnumFormatsTypes type = EnumFormatsTypes.valueOf(format.name()
						.split("_")[0]);

				formatter = buildFormat(buildFormat(format, type), type);
			}
		}

		return formatter;
	}

	private static Format buildFormat(String strFormat, EnumFormatsTypes type) {

		Format format = null;

		switch (type) {

		case DATE:
			format = new SimpleDateFormat(strFormat);
			break;
		case DECIMAL:
			format = new DecimalFormat(strFormat);
			break;
		}

		return format;
	}

	private static String buildFormat(EnumFormats format, EnumFormatsTypes type) {

		String strFormat = EMPTY;
		/*
		 * DATE_DDMMYY, DATE_DDMMYYYY, DATE_YYMMDD, DATE_YYYYMMDD,
		 * DECIMAL_D,DECIMAL_DD, DECIMAL_DDD, DECIMAL_DDDD;
		 */
		String defFormat = format.name().split("_")[1];

		switch (type) {
		case DATE:

			defFormat = defFormat.replaceAll("D", "d");
			strFormat = defFormat.replaceAll("Y", "y");

			break;
		case DECIMAL:

			int lengthToFill = BASE_DECIMAL_FORMAT.length()
					+ countMatches(defFormat, "D");

			strFormat = Fillers.ZERO_RIGHT.fill(
					BASE_DECIMAL_FORMAT, lengthToFill);

			break;
		}

		return strFormat;
	}
}
