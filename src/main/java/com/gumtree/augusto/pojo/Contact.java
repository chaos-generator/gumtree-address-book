package com.gumtree.augusto.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;

/**
 * Represents a contact in the address book.
 * 
 * @author augusto
 */
public class Contact {
    private final String name;
    private final String gender;
    private final DateTime dob;

    public Contact(String name, String gender, DateTime dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public DateTime getDob() {
        return dob;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(gender).append(dob)
                        .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Contact)) {
            return false;
        }
        Contact compareMe = (Contact) obj;
        return new EqualsBuilder()
                        .append(this.getName(), compareMe.getName())
                        .append(this.getGender().toLowerCase(),
                                        compareMe.getGender().toLowerCase())
                        .append(this.getDob(), compareMe.getDob()).isEquals();
    }
}
