package com.gumtree.augusto.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.gumtree.augusto.pojo.Contact;

public class CsvContactDaoTest {

    private ContactDao dao;

    public static final Contact GEMMA = new Contact("Gemma Lane", "Female",
                    new DateTime("1991-11-20"));
    public static final Contact PAUL = new Contact("Paul Robinson", "Male",
                    new DateTime("1985-01-15"));
    public static final Contact BILL = new Contact("Bill McKnight", "Male",
                    new DateTime("1977-03-16"));
    public static final Contact SARAH = new Contact("Sarah Stone", "Female",
                    new DateTime("1980-09-20"));
    public static final Contact WES = new Contact("Wes Jackson", "Male",
                    new DateTime("1974-08-14"));

    @Before
    public void setup() {
        dao = new CsvContactDao("src/main/resources/AddressBook");
    }

    @Test
    public void cannotFindCsv(){
        dao = new CsvContactDao("FileNotFound");
    }

    @Test
    public void getAllContacts_Sucess() {
        List<Contact> contacts = dao.getAllContacts();
        int expectedSize = 5;
        assertEquals(expectedSize, contacts.size());
        assertTrue(contacts.contains(BILL));
        assertTrue(contacts.contains(PAUL));
        assertTrue(contacts.contains(GEMMA));
        assertTrue(contacts.contains(SARAH));
        assertTrue(contacts.contains(WES));
    }

    
    @Test
    public void getAllContacts_FaultyCsv() {
        dao = new CsvContactDao("src/main/resources/FaultyAddressBook");
        List<Contact> contacts = dao.getAllContacts();
        int expectedSize = 3;
        assertEquals(expectedSize, contacts.size());
        assertTrue(contacts.contains(GEMMA));
        assertTrue(contacts.contains(SARAH));
        assertTrue(contacts.contains(WES));
    }
    
}
