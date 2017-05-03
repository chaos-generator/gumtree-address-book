package com.gumtree.augusto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.Days;

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

    /**
     * Retrieves a list of contacts filter by gender.
     * 
     * @param gender
     *            the gender to be included in the results.
     * @return A list of contacts that matches the gender informed.
     */
    @Override
    public List<Contact> findByGender(String gender) {
        return dao.getAllContacts().stream()
                        .filter(c -> c.getGender().equals(gender))
                        .collect(Collectors.toList());
    }

    /**
     * Gets a list of contacts sorted by Date of Birth.
     * 
     * @return a list of contacts sorted by Date of Birth.
     */
    @Override
    public List<Contact> getContactsSortedByDob() {
        return dao.getAllContacts()
                        .stream()
                        .sorted((e1, e2) -> Long.compare(e1.getDob()
                                        .getMillis(), e2.getDob().getMillis()))
                        .collect(Collectors.toList());
    }

    /**
     * Returns the difference in days between the birthdates of two contacts.
     * 
     * @param youngerContact
     *            A string with the full name of the younger Contact
     * @param olderContact
     *            A string with the full name of the older Contact
     * @return the number of days between the contacts DOB. If a negative number
     *         is return, the informed younger and older contacts have been
     *         switched.
     */
    @Override
    public int getDifferenceInDays(String youngerContact, String olderContact) {
        List<Contact> younger = findByName(youngerContact);
        List<Contact> older = findByName(olderContact);
        if (younger.size() <= 0 || older.size() <= 0) {
            // TODO log error, as user was not found.
            return 0;
        }
        // For now, assumes we have only one contact with a given name.
        // TODO handle multiple contacts with the same name
        return getDifferenceInDays(younger.get(0), older.get(0));
    }

    /**
     * Returns the difference in days between the birthdates of two contacts.
     * 
     * @param youngerContact
     *            the younger Contact
     * @param olderContact
     *            the older Contact
     * @return the number of days between the contacts DOB. If a negative number
     *         is return, the informed younger and older contacts have been
     *         switched.
     */
    @Override
    public int getDifferenceInDays(Contact youngerContact, Contact olderContact) {
        return Days.daysBetween(olderContact.getDob(), youngerContact.getDob())
                        .getDays();
    }
}
