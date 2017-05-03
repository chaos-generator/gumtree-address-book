package com.gumtree.augusto.service;

import java.util.List;

import com.gumtree.augusto.pojo.Contact;

/**
 * The service interface.
 * 
 * @author augusto
 */
public interface ContactService {

    /**
     * 1. How many males are in the address book? <br>
     * 2. Who is the oldest person in the address book?<br>
     * 3. How many days older is Bill than Paul?
     */

    /**
     * Retrieves a list of contacts with the given name.<br>
     * 
     * @param name
     * @return
     */
    List<Contact> findByName(String name);

    /**
     * Retrieves a list of contacts with the given gender.<br>
     * 
     * @param gender
     * @return
     */
    List<Contact> findByGender(String gender);

    /**
     * Retrieves a list of contacts sorted by date of birth.
     * 
     * @return a list of contacts sorted by date of birth.
     */
    List<Contact> getContactsSortedByDob();

    /**
     * Difference of days between to contacts
     * 
     * @param youngerContact
     * @param olderContact
     * 
     * @return the age difference in days between the two contacts. If a negative
     *         number is return, it means that the younger and older contacts
     *         have been switched.
     */
    int getDifferenceInDays(Contact youngerContact, Contact olderContact);

    /**
     * Difference of days between to contacts
     * 
     * @param youngerContact
     * @param olderContact
     * 
     * @return the age difference in days between the two contacts. If a negative
     *         number is return, it means that the younger and older contacts
     *         have been switched.
     */
    int getDifferenceInDays(String youngerContact, String olderContact);
}