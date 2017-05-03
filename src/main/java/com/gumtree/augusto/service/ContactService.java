package com.gumtree.augusto.service;

import java.util.List;

import com.gumtree.augusto.pojo.Contact;

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
     * @return a list of contacts sorted by date of birth.
     */
    List<Contact> getContactsSortedByDob();
}