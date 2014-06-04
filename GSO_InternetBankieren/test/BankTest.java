/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BlueHands
 */
public class BankTest {
    
    Bank bank;
    
    public BankTest()
    {
        bank = new Bank("BlueBank");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void openRekening()
    {
        //nakijken of input correct is
        assertEquals("Lege naam is niet toegestaan", -1, bank.openRekening("", "Blerick"));
        assertEquals("Lege woonplaats is niet toegestaan", -1, bank.openRekening("Tom", ""));
        
        //nakijken of rekening aangemaakt wordt
        assertFalse("Rekening niet goed aangemaakt", (-1 == bank.openRekening("Tom", "Blerick")));
    }

    @Test
    public void maakOver()
    {
        /**
     * er wordt bedrag overgemaakt van de bankrekening met nummer bron naar de
     * bankrekening met nummer bestemming, mits het afschrijven van het bedrag
     * van de rekening met nr bron niet lager wordt dan de kredietlimiet van deze
     * rekening 
     * 
     * @param bron
     * @param bestemming
     *            ongelijk aan bron
     * @param bedrag
     *            is groter dan 0
     * @return <b>true</b> als de overmaking is gelukt, anders <b>false</b>
     * @throws NumberDoesntExistException
     *             als een van de twee bankrekeningnummers onbekend is
     */
    }
}
