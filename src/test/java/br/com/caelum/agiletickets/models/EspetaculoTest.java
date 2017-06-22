package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveriaCriarApenasUmaSessaoSeDatasDeInicioEFimForemIguaisEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, hoje, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
		Assert.assertEquals(impeachment, criadas.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), criadas.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriarApenasUmaSessaoSeDatasDeInicioEFimForemIguaisEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, hoje, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(1, criadas.size());
		Assert.assertEquals(impeachment, criadas.get(0).getEspetaculo());
		Assert.assertEquals(hoje.toDateTime(agora), criadas.get(0).getInicio());
	}
	
	@Test
	public void deveriaCriar11SessoesSeIntervaloDeDatasForDe10DiasEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalDate daqui10Dias = hoje.plusDays(10);
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, daqui10Dias, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(11, criadas.size());
		for (int i = 0; i < 11; i++) {
			Assert.assertEquals(impeachment, criadas.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusDays(i).toDateTime(agora), criadas.get(i).getInicio());
		}
	}
	
	@Test
	public void deveriaCriar4SessoesSeIntervaloDeDatasForDe3SemanasEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate hoje = new LocalDate();
		LocalDate daqui3Semanas= hoje.plusWeeks(3);
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(hoje, daqui3Semanas, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(4, criadas.size());
		for (int i = 0; i < 4; i++) {
			Assert.assertEquals(impeachment, criadas.get(i).getEspetaculo());
			Assert.assertEquals(hoje.plusWeeks(i).toDateTime(agora), criadas.get(i).getInicio());
		}
	}
	
	@Test
	public void naoDeveriaCriarSessoesSeDataInicioForMaiorQueDataFimEPeriodicidadeForDiaria() {
		//ENTRADAS
		LocalDate amanha = new LocalDate().plusDays(1);
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(amanha, hoje, agora, Periodicidade.DIARIA);
		
		//SAIDAS
		Assert.assertEquals(0, criadas.size());
	}
	
	@Test
	public void naoDeveriaCriarSessoesSeDataInicioForMaiorQueDataFimEPeriodicidadeForSemanal() {
		//ENTRADAS
		LocalDate amanha = new LocalDate().plusDays(1);
		LocalDate hoje = new LocalDate();
		LocalTime agora = new LocalTime();
		Espetaculo impeachment = new Espetaculo();
		
		//PROCESSAMENTO
		List<Sessao> criadas = impeachment.criaSessoes(amanha, hoje, agora, Periodicidade.SEMANAL);
		
		//SAIDAS
		Assert.assertEquals(0, criadas.size());
	}
}

