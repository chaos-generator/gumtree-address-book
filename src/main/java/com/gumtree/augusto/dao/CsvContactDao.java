package com.gumtree.augusto.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.gumtree.augusto.pojo.Contact;
import com.opencsv.CSVReader;

/**
 * CSV implementation of Contacts DAO.
 * 
 * @author augusto
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
        // Skips loading, if cached already
        if (cache.size() <= 0) {
            readCsv();
        }
        return cache;
    }

    private void readCsv() {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile));) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                parseLine(line);
            }
        } catch (IOException e) {
            // TODO implement a logger
            e.printStackTrace();
        }
    }

    private void parseLine(String[] line) {
        if (!Strings.isNullOrEmpty(line[0]) && !Strings.isNullOrEmpty(line[1])
                        && !Strings.isNullOrEmpty(line[2])) {
            DateTime dob = parseDob(line);

            Contact aContact = new Contact(line[0].trim(), line[1].trim()
                            .toLowerCase(), dob);
            cache.add(aContact);
        } else {
            // TODO implement a logger
            // TODO log a warning
            System.out.println(String
                            .format("Could not read line. One or more "
                                            + "values are null or empty [%s, %s, %s]",
                                            line[0], line[1], line[2]));
        }
    }

    private DateTime parseDob(String[] line) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .append(DateTimeFormat.forPattern("dd/MM/yy"))
                        .toFormatter().withPivotYear(1970)
                        .withLocale(Locale.UK);

        DateTime dob = formatter.parseDateTime(line[2].trim());
        return dob;
    }

}
