package com.gumtree.augusto.dao;

import java.util.List;

import com.gumtree.augusto.pojo.Contact;

/**
 * Interface for Data Access Object
 * 
 * @author augusto
 *
 */
public interface ContactDao {

    /**
     * Retrieves all contacts from the data store
     * 
     * @return list with all contacts in the data store.
     * 
     *         TODO: for large datastores, implement paging.
     */
    List<Contact> getAllContacts();

    /**
     * Retrieves one contact with the given name.<br>
     * 
     * @param name
     * @return
     */
    List<Contact> findByName(String name);

}
