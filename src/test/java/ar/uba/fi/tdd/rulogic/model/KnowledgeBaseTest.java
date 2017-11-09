package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		knowledgeBase = new KnowledgeBase();
		knowledgeBase.parseDB("src/main/resources/rules.db");
	}

	@Test
	public void testFactVerdadero() {
		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));
	}

	@Test
	public void testFactVerdaderoSinEspacios() {
		Assert.assertTrue(this.knowledgeBase.answer("mujer(maria)."));
	}

	@Test
	public void testFactVerdaderoConEspacios() {
		Assert.assertTrue(this.knowledgeBase.answer("mujer (maria) ."));
	}

	@Test
	public void FactFalso() {
		Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));
	}

	@Test
	public void testFactVerdaderoConDosArgumentos() {
		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));
	}

	@Test
	public void testFactVerdaderoConDosArgumentosYEspacios() {
		Assert.assertTrue(this.knowledgeBase.answer("padre (juan,   pepe)   ."));
	}

	@Test
	public void FactFalsoConDosArgumentos() {
		Assert.assertFalse(this.knowledgeBase.answer("padre (juan, miguel)."));
	}

	@Test
	public void testRuleVerdaderaConDosArgumentos() {
		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe,juan)."));
	}

	@Test
	public void testFalseRuleConDosArgumentos() {
		Assert.assertFalse(this.knowledgeBase.answer("hijo(juan,pepe)."));
	}

	@Test
	public void testRuleVerdaderaConTresArgumentos() {
		Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas, cecilia, roberto)."));
	}

	@Test
	public void testFalseRuleConTresArgumentos() {
		Assert.assertFalse(this.knowledgeBase.answer("tio(pepe, cecilia, roberto)."));
	}

	@Test
	public void testQueryIcompletoEsFalso() {
		Assert.assertFalse(this.knowledgeBase.answer("tio(pepe, cecil"));
	}


	@Test
	public void testRuleNoExistenteIsFalse() {
		Assert.assertFalse(this.knowledgeBase.answer("sobrino(pepe, cecilia)"));
	}

}
