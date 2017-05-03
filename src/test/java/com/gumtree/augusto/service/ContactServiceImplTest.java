package com.gumtree.augusto.service;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gumtree.augusto.dao.CsvContactDaoTest;
import com.gumtree.augusto.pojo.Contact;

public class ContactServiceImplTest {

    private ContactService service;

    @Before
    public void setup() {
        service = new ContactServiceImpl("src/main/resources/AddressBook");
    }

    @Test
    public void findByName_Success() {
        List<Contact> contacts = service.findByName("Bill McKnight");
        int expectedSize = 1;
        Assert.assertEquals(expectedSize, contacts.size());
        Assert.assertTrue(contacts.contains(new Contact("Bill McKnight",
                        "Male", new DateTime("1977-03-16"))));

    }

    @Test
    public void findByName_SuccessNameNotFound() {
        List<Contact> contacts = service.findByName("Augusto Uehara");
        int expectedSize = 0;
        Assert.assertEquals(expectedSize, contacts.size());
    }

    @Test
    public void findByGender_SuccessMale() {
        List<Contact> contacts = service.findByGender("male");
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, contacts.size());
    }

    @Test
    public void findByName_SuccessFemale() {
        List<Contact> contacts = service.findByGender("female");
        int expectedSize = 2;
        Assert.assertEquals(expectedSize, contacts.size());
    }

    @Test
    public void getContactsSortedByDob_Success() {
        List<Contact> contacts = service.getContactsSortedByDob();
        int expectedSize = 5;
        Assert.assertEquals(expectedSize, contacts.size());
        Assert.assertEquals(contacts.get(0), CsvContactDaoTest.WES);
        Assert.assertEquals(contacts.get(4), CsvContactDaoTest.GEMMA);
    }
}
