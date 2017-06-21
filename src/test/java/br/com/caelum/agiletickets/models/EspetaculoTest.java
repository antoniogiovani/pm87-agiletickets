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
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	@Test
	public void DeveInformarQueNaoEhPossivelSessaoDiariaComMaisDeUmDia() {
		Espetaculo show = new Espetaculo();
		LocalDate ini = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime hora = new LocalTime();
		Periodicidade diaria = Periodicidade.DIARIA;
		List<Sessao> sessoes = show.criaSessoes(ini, fim, hora, diaria);
		
		
		show.criaSessoes(ini, fim, hora, diaria);
		Assert.assertEquals(1,sessoes.size());
		
		Sessao unica = sessoes.get(0);
		Assert.assertEquals(show,unica.getEspetaculo());
		Assert.assertEquals(ini.toDateTime(hora), unica.getInicio());
	}
	
	@Test
	public void DeveInformarQueNaoEhPossivelSessaoSemanalComZeroSemana() {
		Espetaculo show = new Espetaculo();
		LocalDate ini = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime hora = new LocalTime();
		Periodicidade semana = Periodicidade.SEMANAL;
		List<Sessao> sessoes = show.criaSessoes(ini, fim, hora, semana);
				
		show.criaSessoes(ini, fim, hora, semana);
		Assert.assertEquals(0,sessoes.size());
		
		Sessao unica = sessoes.get(0);
		Assert.assertEquals(show,unica.getEspetaculo());
		Assert.assertEquals(ini.toDateTime(hora), unica.getInicio());
	}
	
	
}
