/** 3-2 Milestone: Contact Service_Abdel Ortiz_09-20-2026*/

import java.util.HashMap;
import java.util.Map;

// Contacts are stored in memory while this service is in use.
public class ContactService {
    // Each ID is the key used to find its contact.
    private final Map<String, Contact> contacts = new HashMap<>();

    // I check that the contact is not null and its ID is not already in use.
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact must not be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("contactId already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    // I look up the contact by ID and throw an exception if it does not exist.
    public Contact getContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("contactId does not exist");
        }
        return contact;
    }

    public void deleteContact(String contactId) {
        // Before deleting, I check that the ID exists.
        getContact(contactId);
        contacts.remove(contactId);
    }

    // I find the contact and use its setter to check the new first name.
    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getContact(contactId);
        contact.setFirstName(firstName);
    }

    // Only the last name of the selected contact is changed.
    public void updateLastName(String contactId, String lastName) {
        Contact contact = getContact(contactId);
        contact.setLastName(lastName);
    }

    // The setter checks that the new phone number has 10 digits.
    public void updatePhone(String contactId, String phone) {
        Contact contact = getContact(contactId);
        contact.setPhone(phone);
    }

    // The setter checks the address before saving the change.
    public void updateAddress(String contactId, String address) {
        Contact contact = getContact(contactId);
        contact.setAddress(address);
    }
}