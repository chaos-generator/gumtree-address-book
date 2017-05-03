package com.gumtree.augusto;

import java.util.List;

import com.gumtree.augusto.pojo.Contact;
import com.gumtree.augusto.service.ContactService;
import com.gumtree.augusto.service.ContactServiceImpl;

/**
 * Main class for the application. This could be use Spring boot and become a
 * rest api, but this was out of the scope of this test.
 * 
 * @author augusto
 */
public class App {

    /**
     * 1. How many males are in the address book? <br>
     * 2. Who is the oldest person in the address book?<br>
     * 3. How many days older is Bill than Paul?
     */
    public static void main(String[] args) {
        String csvFile = "src/main/resources/AddressBook";
        if (args != null && args.length > 0) {
            csvFile = args[0];
        }
        ContactService service = new ContactServiceImpl(csvFile);

        howManyMales(service);
        System.out.println("");
        whosTheOldestContact(service);
        System.out.println("");
        howManyDaysOlder(service);

    }

    private static void howManyDaysOlder(ContactService service) {
        int days = service
                        .getDifferenceInDays("Paul Robinson", "Bill McKnight");
        System.out.println(String.format("Bill is %s days older than Paul.",
                        days));
    }

    private static void whosTheOldestContact(ContactService service) {
        List<Contact> contactsSortedByDob = service.getContactsSortedByDob();
        if (contactsSortedByDob != null && contactsSortedByDob.size() > 0)
            System.out.println(String.format("%s is your oldest contact.",
                            contactsSortedByDob.get(0).getName()));
    }

    private static void howManyMales(ContactService service) {
        List<Contact> maleContacts = service.findByGender("male");
        if (maleContacts != null && maleContacts.size() > 0)
            System.out.println(String.format(
                            "There are %s male contacts in your address book.",
                            maleContacts.size()));
    }
}
