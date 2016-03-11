package br.com.knowrad.patologia;

import br.com.knowrad.entity.patologia.Caso;
import br.com.knowrad.entity.patologia.CasoModalidade;
import br.com.knowrad.entity.patologia.PatologiaCaso;
import br.com.knowrad.service.patologia.CasoModalidadeService;
import br.com.knowrad.service.patologia.CasoService;
import br.com.knowrad.service.patologia.PatologiaCasoService;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/coreContext.xml" })
@TransactionConfiguration(defaultRollback = true)
public class CasoTest {

    @Autowired
    private CasoService service;

    @Autowired
    private CasoModalidadeService casoModalidadeService;

    @Autowired
    private PatologiaCasoService patologiaCasoService;

    @Test
    @Ignore
    public void persistManyTest() throws Exception {
        for(int i = 0; i <= 100; i++) {
            Caso c = new Caso();
            c.setTitulo("titulo" + Math.random());
            c.setLaudo("textao do laudo aqui hehehehehehehe" + Math.random());
            service.persist(c);
            System.out.println("Caso nº " + c.getIdCaso() + " persistido.");
        }
    }

    @Test
    @Ignore
    public void persistTest() throws Exception {
        Caso c = new Caso();
        c.setTitulo("titulo");
        c.setLaudo("textao do laudo aqui hehehehehehehe");
        service.persist(c);

        TestCase.assertNotNull(c.getIdCaso());
        TestCase.assertTrue(c.getIdCaso() > 0);
    }

    @Test
    @Ignore
    public void mergeTest() throws Exception {
        Caso c = service.findById(new Long(1));
        c.setTitulo(c.getTitulo() + "-alterado");
        c.setLaudo(c.getLaudo() + "-alterado");
        service.merge(c);
    }

    @Test
    @Ignore
    public void removeTest() throws Exception {
        Caso c = service.findById(new Long(1));
        service.remove(c.getIdCaso());
    }

    @Test
    @Ignore
    public void removeFullTest() throws Exception {
        Long idCaso = new Long(102);

        //CasoModalidade
        List<CasoModalidade> listCasoModalidade = casoModalidadeService.findAllByIdCaso(idCaso);
        for(CasoModalidade casoModalidade : listCasoModalidade)
            casoModalidadeService.remove(casoModalidade.getIdCasoModalidade());

        //PatologiaCaso
        List<PatologiaCaso> listPatologiaCaso = patologiaCasoService.findAllByIdCaso(idCaso);
        for(PatologiaCaso patologiaCaso : listPatologiaCaso)
            patologiaCasoService.remove(patologiaCaso.getIdPatologiaCaso());

        Caso caso = service.findById(idCaso);
        service.remove(caso.getIdCaso());
    }

    @Test
    @Ignore
    public void findByIdTest() throws Exception {
        Long id = new Long(1);
        Caso c = service.findById(id);

        TestCase.assertNotNull(c);
        TestCase.assertTrue(c.getIdCaso() > 0);
    }

}
