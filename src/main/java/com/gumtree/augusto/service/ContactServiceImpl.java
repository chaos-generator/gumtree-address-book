package com.gumtree.augusto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.junit.Before;

import com.gumtree.augusto.dao.ContactDao;
import com.gumtree.augusto.dao.CsvContactDao;
import com.gumtree.augusto.pojo.Contact;

public class ContactServiceImpl implements ContactService {

    private ContactDao dao;

    public ContactServiceImpl(String datasource) {
        dao = new CsvContactDao(datasource);
    }

    /**
     * Retrieves a list of users with the given name.
     * 
     * @param name
     *            A name to search in the contact list
     * @return a list with the contacts with the given name.
     */
    @Override
    public List<Contact> findByName(String name) {
        return dao.getAllContacts().stream()
                        .filter(c -> c.getName().equals(name))
                        .collect(Collectors.toList());
    }

    @Override
    public List<Contact> findByGender(String gender) {
        return dao.getAllContacts().stream()
                        .filter(c -> c.getGender().equals(gender))
                        .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getContactsSortedByDob() {
        return dao.getAllContacts()
                        .stream()
                        .sorted((e1, e2) -> Long.compare(e1.getDob().getMillis(),
                                        e2.getDob().getMillis()))
                        .collect(Collectors.toList());
    }
}
