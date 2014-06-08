/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.internettoegang.Balie;
import bank.internettoegang.IBankiersessie;
import java.rmi.RemoteException;
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
public class BalieTest {
    
    Balie balie;
    Bank bank;
    private boolean IBalieSessie;
    
    public BalieTest()
    {
        try
        {
            bank = new Bank("ABNAmro");
            balie = new Balie(bank);
        }
        catch (RemoteException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp()
    {
        balie.openRekening("Klaas", "Venlo", "Test1");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void openRekeningTest()
    {
        //lege naam
        assertEquals("Lege naam is niet toegestaan", null, balie.openRekening("", "Blerick", "Test1"));
        
        //lege plaats
        assertEquals("Lege plaats is niet toegestaan", null, balie.openRekening("Tom", "", "Test1"));
        
        //ww korter als 4
        assertEquals("Wachtwoord moet langer als 4 characters zijn", null, balie.openRekening("Tom", "Blerick", "Tes"));
        
        //ww langer als 8
        assertEquals("Wachtwoord moet korter als 8 characters zijn", null, balie.openRekening("Tom", "Blerick", "Test12345"));
        
        //return
        assertEquals("Account wordt niet goed gegenereerd", 8, balie.openRekening("Tom", "Blerick", "Test1").length());
        
        //nullpointer als bank een interface is
    }

  /**
   * er wordt een sessie opgestart voor het login-account met de naam
   * accountnaam mits het wachtwoord correct is
   * @param accountnaam
   * @param wachtwoord
   * @return de gegenereerde sessie waarbinnen de gebruiker 
   * toegang krijgt tot de bankrekening die hoort bij het betreffende login-
   * account mits accountnaam en wachtwoord matchen, anders null
   */
    @Test
    public void logInTest() throws RemoteException
    {
        //lege naam
        assertEquals("Account naam mag niet leeg zijn", null, balie.logIn("", "Test1"));
        
        //leeg ww
        assertEquals("Wachtwoord mag niet leeg zijn ", null, balie.logIn("Klaas", ""));
        
        //return
        //IBankiersessie bankierSessie = balie.logIn("Klaas", "Test1");
        //assertTrue("Login niet gelukt", bankierSessie.getClass() == IBankiersessie.class);
    }
}
