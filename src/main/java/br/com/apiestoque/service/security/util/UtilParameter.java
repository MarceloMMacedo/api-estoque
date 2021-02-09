package br.com.apiestoque.service.security.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.service.security.exceptions.AuthorizationException;
import br.com.apiestoque.utils.DataClintecnpj;
 

public class UtilParameter {

	public static BaseEntity clonarentity(BaseEntity fonte, BaseEntity destino, String[] campos) {
		Class classefonte = fonte.getClass();
		Class classedestino = destino.getClass();

		Field fldfonte = null;
		Field fldDestino = null;
		for (String string : campos) {

			try {

				fldfonte = classefonte.getDeclaredField(string);
				fldfonte.setAccessible(true);

				fldDestino = classedestino.getDeclaredField(string);
				fldDestino.setAccessible(true);
//
				if (fldfonte != null) {
//					
//					//Pegar valor fonte
					fldfonte.setAccessible(true);
					Object value = fldfonte.get(fonte);
//
//					//Escrever Valor destino
					if (value != null) {
						fldDestino.setAccessible(true);
						fldDestino.set(destino, value);
					}
				}
			} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
				// System.out.println(e);
			}
		}

		return destino;
	}

	public static BaseEntity clonarentity(BaseEntity fonte, BaseEntity destino) {

		Class classefonte = fonte.getClass();
		Class classedestino = destino.getClass();

		Field fldfonte = null;
		Field fldDestino = null;
		Field[] fs = classefonte.getDeclaredFields();
		for (Field f : fs) {
			String string = f.getName();

			try {

				fldfonte = classefonte.getDeclaredField(string);
				fldfonte.setAccessible(true);

				fldDestino = classedestino.getDeclaredField(string);
				fldDestino.setAccessible(true);

				if (fldfonte != null) {

					// Pegar valor fonte
					fldfonte.setAccessible(true);
					Object value = fldfonte.get(fonte);

					// Escrever Valor destino
					if (value != null) {
						fldDestino.setAccessible(true);
						fldDestino.set(destino, value);
					}
				}
			} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
				System.out.println(e);
			}
		}

		return destino;
	}
	
	public static BaseEntity clonarentity(BaseEntity fonte, BaseEntity destino,Class classe) {

		Class classefonte = classe;
		Class classedestino = classe;

		Field fldfonte = null;
		Field fldDestino = null;
		Field[] fs = classe.getDeclaredFields();
		for (Field f : fs) {
			String string = f.getName();

			try {

				fldfonte = classe.getDeclaredField(string);
				fldfonte.setAccessible(true);

				fldDestino = classe.getDeclaredField(string);
				fldDestino.setAccessible(true);

				if (fldfonte != null) {
					if (!f.isAnnotationPresent(Transient.class)) {
					// Pegar valor fonte
					fldfonte.setAccessible(true);
					Object value = fldfonte.get(fonte);

					// Escrever Valor destino
					if (value != null) {
						fldDestino.setAccessible(true);
						fldDestino.set(destino, value);
					}
				}}
			} catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
				System.out.println(e);
			}
		}

		return destino;
	}

	public static DataClintecnpj findendresscnpj(String cnpj) {
		// curl -X GET https://www.receitaws.com.br/v1/cnpj/27865757000102
		BufferedReader reader;
		DataClintecnpj cnpjEmpres = null;
		try {
			URL url = new URL("https://www.receitaws.com.br/v1/cnpj/" + cnpj);

			reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String data = reader.readLine();
			String s = "{\"status\":\"ERROR\",\"message\":\"CNPJ inválido\"}".toLowerCase();
			if (!data.toLowerCase().equals(s)) {
				cnpjEmpres = new DataClintecnpj(parseSemGson(data, "text"), parseSemGson(data, "code"),
						parseSemGson(data, "data_situacao"), parseSemGson(data, "complemento"),
						parseSemGson(data, "tipo"), parseSemGson(data, "nome"), parseSemGson(data, "telefone"),
						parseSemGson(data, "situacao"), parseSemGson(data, "bairro"), parseSemGson(data, "logradouro"),
						parseSemGson(data, "numero"), parseSemGson(data, "cep"), parseSemGson(data, "municipio"),
						parseSemGson(data, "porte"), parseSemGson(data, "natureza_juridica"),
						parseSemGson(data, "cnpj"), parseSemGson(data, "uf"), parseSemGson(data, "capital_social"));
			}
			System.out.println(cnpjEmpres);
			reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			for (String line; (line = reader.readLine()) != null;) {
				System.out.println(line);
			}

			reader.close();
		} catch (IOException ioe) {
			throw new AuthorizationException(ioe.getMessage());
		}

		return cnpjEmpres;
	}

	public static String parseSemGson(String linha, String campo) {
		linha = linha.replaceAll("\"", "");
		int indice = linha.indexOf(campo) + 1 + campo.length();
		int ultimo_char = linha.indexOf(",", indice) != -1 ? linha.indexOf(",", indice) : linha.indexOf("}", indice);
		return linha.substring(indice, ultimo_char);
	}

	public static BaseEntity complemenForm(BaseEntity banco, BaseEntity formulario, Class classe) {
		//this.formulario = formulario;
		/*if (banco == null)
			try {
				banco = (Serializable) classe.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		this.banco = banco;*/
	
		String tipoPrimario;
		Object valor = null;
		List<Field> camposAtualizaveis = new LinkedList<>();
		Field listaCampo[] = classe.getDeclaredFields();
		for (int i = 0; i < listaCampo.length; i++) {
			Field fld = listaCampo[i];
			fld.setAccessible(true);
			try {
				valor = fld.get(formulario);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String id= fld.getName();//.getSimpleName();
			if (valor != null) {
				if (  !id.equals("id")) {
					tipoPrimario = fld.getType().getSimpleName();
					switch (tipoPrimario) {
					case "double":
						if ((double) valor != 0.0)
							try {
								fld.setDouble(banco, (double) valor);
							} catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						break;
						
					case "Integer":
						if ((Integer) valor != 0)
							try {
								fld.setInt(banco, (Integer) valor);
							} catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						break;
					case "int":
						if ((int) valor != 0)
							try {
								fld.setInt(banco, (int) valor);
							} catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						break;
					case "Double":
						if ((double) valor != 0.0)
							try {
								double v=(double) valor;
								fld.setDouble(banco,v);
							} catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						break;
					case "Date":
						try {
							fld.set(banco, valor);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "String":
						try {
							fld.set(banco, valor);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					default:
						break;
					}
				}
			}
		}
		return banco;

		// System.out.println("Interfaces = " + Arrays.asList(i));
	}
}
