package com.gumtree.augusto.dao;

import java.util.List;

import com.gumtree.augusto.pojo.Contact;

public interface ContactDao {

    public List<Contact> getAllContacts();

    public List<Contact> findByName(String name);

}
