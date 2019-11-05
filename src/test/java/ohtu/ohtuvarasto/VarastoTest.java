package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    @Test
    public void negatiivinenTilavuus() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void tilavuusNollaTaiNegatiivinen() {
        varasto = new Varasto(2, 1);
        assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(0, 2);
        assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(0, -1);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    @Test
    public void lisaaVarastoonMaaraNolla() {
        varasto = new Varasto(1, 1);
        varasto.lisaaVarastoon(-1);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
   
    @Test
    public void ylimaarainenHukkaan() {
        varasto = new Varasto(2, 1);
        varasto.lisaaVarastoon(2);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonYlimaarainenHukkaan() {
        varasto = new Varasto(10, 0);
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void otaVarastostaPikaPoistuminen() {
        varasto = new Varasto(10, 5);
        assertEquals(0.0, varasto.otaVarastosta(-2), vertailuTarkkuus);
       
        
    }
    @Test
    public void otaVarastostaTyhjenee() {
       varasto = new Varasto(10, 5);
        assertEquals(5, varasto.otaVarastosta(6), vertailuTarkkuus); 
        
    }
  
    

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}