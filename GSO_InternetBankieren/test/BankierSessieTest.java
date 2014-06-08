/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import bank.internettoegang.Bankiersessie;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
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
public class BankierSessieTest {
    
    Bankiersessie sessie;
    Bank bank;
    
    public BankierSessieTest() throws RemoteException
    {
        bank = new Bank("ABNAmro");
        sessie = new Bankiersessie(100000, bank);
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

	/**
	 * er wordt bedrag overgemaakt van de bankrekening met het nummer bron naar
	 * de bankrekening met nummer bestemming
	 * 
	 * @param bron
	 * @param bestemming
	 *            is ongelijk aan rekeningnummer van deze bankiersessie
	 * @param bedrag
	 *            is groter dan 0
	 * @return <b>true</b> als de overmaking is gelukt, anders <b>false</b>
	 * @throws NumberDoesntExistException
	 *             als bestemming onbekend is
	 * @throws InvalidSessionException
	 *             als sessie niet meer geldig is 
	 */
        
        @Test
	public void maakOverTest() throws InterruptedException
        {
            //rekening nummer mag niet het zelfde zijn als van de sessie
            try
            {
                assertFalse("Bestemming moet ongelijk zijn aan de eigen rekening", sessie.maakOver(100000, new Money(100, "Euro")));
                fail("Besteming moet ongelijk zijn aan eigen rekening");
            }
            catch (RuntimeException | NumberDoesntExistException | InvalidSessionException | RemoteException ex)
            {
            }
            
            //Bedrag mag niet kleiner als 0 zijn
            try
            {
                assertFalse("Bedrag mag niet kleiner als 0 zijn", sessie.maakOver(100001, new Money(-100, "Euro")));
                fail("Bedrag mag niet kleiner als 0 zijn");
            }
            catch (RuntimeException | NumberDoesntExistException | InvalidSessionException | RemoteException ex)
            {
            }
            
            //return
            try
            {
                assertFalse("Bedrag is niet overgemaakt", sessie.maakOver(100001, new Money(100, "Euro")));
            }
            catch (RuntimeException | NumberDoesntExistException | InvalidSessionException | RemoteException ex)
            {
            }
            
            //Controleren of een sessie verloop na de opgegeven tijd
            try
            {
                Thread.sleep(60000);
                assertFalse("Sessie is niet meer geldig", sessie.maakOver(100001, new Money(100, "Euro")));
                fail("Sessie is niet meer geldig");
            }
            catch (RuntimeException | NumberDoesntExistException | InvalidSessionException | RemoteException ex)
            {
            }
        }
}
