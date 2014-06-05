/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        bank.openRekening("Tom", "Blerick");
        bank.openRekening("Frank", "Sevenum");
        
        int rekeningNr1 = 100000000;
        int rekeningNr2 = 100000001;
        
        try
        {
            //controleren of het mogelijk is naar je eigen bankrekening geld over te maken
            assertFalse("Kan niet overmaken naar eigen rekening", bank.maakOver(rekeningNr1, rekeningNr1, new Money(100, "euro")));
            fail("Kan niet overmaken naar eigen rekening");
        }
        catch (RuntimeException | NumberDoesntExistException ex)
        {
        }
        
        try
        {
            //controleren of het mogelijk is over te maken naar een niet bestaande rekening
            assertFalse("Rekening bestaat niet", bank.maakOver(rekeningNr1, rekeningNr2 + 1, new Money(100, "Euro")));
            fail("Rekening bestaat niet");
        }
        catch (RuntimeException | NumberDoesntExistException ex)
        {
        }
        
        try
        {
            assertFalse("Bedrag is kleiner als 0", bank.maakOver(rekeningNr1, rekeningNr2, new Money(-100, "euro")));
            fail("Bedrag is kleiner als 0");
        }
        catch (RuntimeException | NumberDoesntExistException ex)
        {
        }
        
        try 
        {
            //controleren of correcte overboeking lukt
            assertTrue("Overboeking is niet gelukt", bank.maakOver(rekeningNr1, rekeningNr2, new Money(100, "Euro")));
            fail("Overboeking is niet gelukt");
        }
        catch (RuntimeException | NumberDoesntExistException ex)
        {
        }
    }
}
