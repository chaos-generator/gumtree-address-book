package com.gumtree.augusto.dao;

import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.gumtree.augusto.pojo.Contact;
import com.opencsv.CSVReader;

/**
 * 
 * @author augusto
 *
 */
public class CsvContactDao implements ContactDao {

    private String csvFile;
    // TODO limit size of the cache, and allow it to be configurable
    private List<Contact> cache = Lists.newArrayList();

    public CsvContactDao(String csvFile) {
        this.csvFile = csvFile;
    }

    /**
     * Reads all all the contacts from the CSV and add them to a cache,
     * currently limited to 1000 elements.
     */
    @Override
    public List<Contact> getAllContacts() {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile));) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                Contact aContact = new Contact(line[0], line[1],
                                ZonedDateTime.parse(line[2], DateTimeFormatter
                                                .ofPattern("dd/MM/yyyy")));
                // If there is no name, other functions of the system won't
                // work, so consider this a problem.
                if (!Strings.isNullOrEmpty(line[0])) {
                    cache.add(aContact);
                } else {
                    // TODO implement a logger
                    // TODO log a warning
                    System.out.println("Could not ");
                }
            }
        } catch (IOException e) {
            // TODO implement a logger
            e.printStackTrace();
        }
        return null;
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
        // if cache is empty, try loading it first.
        if (cache.size() <= 0) {
            getAllContacts();
        }
        return cache.stream().filter(c -> c.getName().equals(name))
                        .collect(Collectors.toList());
    }
}
