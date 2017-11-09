package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Nicoo on 8/11/2017.
 */
public class IcompleteDataBaseTest {

    @InjectMocks
    private KnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        knowledgeBase = new KnowledgeBase();
        knowledgeBase.parseDB("src/main/resources/incompleteDatabase.db");
    }

    @Test
    public void testFactVerdadero() {
        Assert.assertFalse(this.knowledgeBase.answer("varon(juan)."));
    }
}
