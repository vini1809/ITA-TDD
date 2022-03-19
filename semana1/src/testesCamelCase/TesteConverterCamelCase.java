package testesCamelCase;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import camelCase.ConvertException;
import camelCase.ConverterCamelCase;

public class TesteConverterCamelCase {

	private ConverterCamelCase ccc;
	private List<?> lstTeste;
	
	@Before
	public void inicializaString(){
		ccc = new ConverterCamelCase("Teste");
	}
	@Test(expected=ConvertException.class)
	public void testeException(){
		ccc.converterCamelCase(null);
		ccc.converterCamelCase("");
		ccc.converterCamelCase(" ");
		ccc.converterCamelCase("10Primeiros");
		ccc.converterCamelCase("nome#Composto");
	}
	@Test
	public void testeConverteCamelCase(){
		//string simples
		lstTeste = ccc.converterCamelCase("nome");
		assertEquals(lstTeste.size(), 1);
		assertEquals(lstTeste.get(0), "nome");
		//string camelCase
		lstTeste = ccc.converterCamelCase("nomeComposto");
		assertEquals(lstTeste.size(), 2);
		assertEquals(lstTeste.get(0), "nome");
		assertEquals(lstTeste.get(1), "composto");
		//string CamelCase
		lstTeste = ccc.converterCamelCase("NomeComposto");
		assertEquals(lstTeste.size(), 2);
		assertEquals(lstTeste.get(0), "nome");
		assertEquals(lstTeste.get(1), "composto");	
		//string SIMPLES
		lstTeste = ccc.converterCamelCase("CPF");
		assertEquals(lstTeste.size(), 1);
		assertEquals(lstTeste.get(0), "CPF");
		//string numeroCPF
		lstTeste = ccc.converterCamelCase("numeroCPF");
		assertEquals(lstTeste.size(), 2);
		assertEquals(lstTeste.get(0), "numero");
		assertEquals(lstTeste.get(1), "CPF");
		//string numeroCPFContribuinte
		lstTeste = ccc.converterCamelCase("numeroCPFContribuinte");
		assertEquals(lstTeste.size(), 3);
		assertEquals(lstTeste.get(0), "numero");
		assertEquals(lstTeste.get(1), "CPF");
		assertEquals(lstTeste.get(2), "contribuinte");
		//string recupera10Primeiros 
		lstTeste = ccc.converterCamelCase("recupera10Primeiros");
		assertEquals(lstTeste.size(), 3);
		assertEquals(lstTeste.get(0), "recupera");
		assertEquals(lstTeste.get(1), "10");
		assertEquals(lstTeste.get(2), "primeiros");		
	}
	@Test
	public void testeStrNull(){
		assertFalse(ccc.isStringValida(""));
		assertFalse(ccc.isStringValida(" "));
		assertFalse(ccc.isStringValida(null));
	}
	//como isStringValida utiliza o length fa�o um teste com o tamanho tamb�m
	@Test
	public void testeStrTamanho(){
		Assert.assertEquals(5, ccc.tamanhoString("abcde "));
	}

	//Ap�s validar se a string informada � v�lida vamos come�ar os testes de requisi��o
	

	@Test
	public void testeIniciaComNumero(){
		assertTrue(ccc.iniciaComNumero("1-iniciaComNumero"));
		assertFalse(ccc.iniciaComNumero("iniciaComNummeroNao"));

	}
	@Test
	public void testeTemCharEspecial(){
		assertTrue(ccc.temCharEspecial("11abcde#fg"));
		assertFalse(ccc.temCharEspecial("temCharEspecialFalse"));

	}
	@Test
	public void testeTemNumeroEntreAString(){
		assertTrue(ccc.temNumeroEntreAString("abcdef123ghij"));
		assertFalse(ccc.temNumeroEntreAString("nenhumNumeroEntreAString"));
	}
	@Test
	public void testeNumeroEntreStringCamelCase(){
		lstTeste = ccc.converterCamelCase("recupera10Primeiros");
		assertEquals(lstTeste.size(), 3);
		assertEquals(lstTeste.get(1), "10");
	}


}
