/** 3-2 Milestone: Contact Service_Abdel Ortiz_09-20-2026*/

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private ContactService service;

    // I create valid contacts with different IDs for the tests.
    private Contact contact(String id) {
        return new Contact(id, "Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    // Each test starts with a new service so it does not affect the other tests.
    @BeforeEach
    void setUp() {
        service = new ContactService();
        service.addContact(contact("1"));
        service.addContact(contact("2"));
    }

    // I check that added contacts can be found by their IDs.
    @Test
    void addsAndRetrievesContactsById() {
        assertEquals("1", service.getContact("1").getContactId());
        assertEquals("2", service.getContact("2").getContactId());
        assertEquals("Abdel", service.getContact("1").getFirstName());
    }

    // A duplicate ID should be rejected without replacing the original contact.
    @Test
    void rejectsDuplicateIdWithoutOverwritingOriginal() {
        Contact original = service.getContact("1");
        Contact duplicate = new Contact("1", "Other", "Person", "9876543210", "Other address");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
        assertSame(original, service.getContact("1"));
        assertEquals("Abdel", service.getContact("1").getFirstName());
    }

    @Test
    void rejectsNullContact() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    // When one contact is deleted, the other should still be available.
    // I also check that the deleted ID can be used again.
    @Test
    void deletesOnlyRequestedContactAndAllowsIdReuse() {
        service.deleteContact("1");
        assertThrows(IllegalArgumentException.class, () -> service.getContact("1"));
        assertEquals("2", service.getContact("2").getContactId());
        service.addContact(contact("1"));
        assertEquals("1", service.getContact("1").getContactId());
    }

    // I test each update separately to check which field changes.
    @Test
    void updatesFirstNameOnly() {
        service.updateFirstName("1", "ABCDEFGHIJ");
        assertDetails("ABCDEFGHIJ", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void updatesLastNameOnly() {
        service.updateLastName("1", "ABCDEFGHIJ");
        assertDetails("Abdel", "ABCDEFGHIJ", "0123456789", "123 Main St");
    }

    @Test
    void updatesPhoneOnly() {
        service.updatePhone("1", "0000000000");
        assertDetails("Abdel", "Ortiz", "0000000000", "123 Main St");
    }

    @Test
    void updatesAddressOnly() {
        service.updateAddress("1", "123456789012345678901234567890");
        assertDetails("Abdel", "Ortiz", "0123456789", "123456789012345678901234567890");
    }

    // I reuse these checks to verify the updated contact and the second contact.
    private void assertDetails(String first, String last, String phone, String address) {
        Contact contact = service.getContact("1");
        Contact other = service.getContact("2");
        assertEquals("1", contact.getContactId());
        assertEquals(first, contact.getFirstName());
        assertEquals(last, contact.getLastName());
        assertEquals(phone, contact.getPhone());
        assertEquals(address, contact.getAddress());
        assertEquals("2", other.getContactId());
        assertEquals("Abdel", other.getFirstName());
        assertEquals("Ortiz", other.getLastName());
        assertEquals("0123456789", other.getPhone());
        assertEquals("123 Main St", other.getAddress());
    }

    // The operations should fail if the ID is null or is not registered.
    @Test
    void rejectsMissingIdsForAllOperationsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.getContact(null));
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName(null, "A"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName(null, "B"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone(null, "9876543210"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress(null, "Home"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsMissingIdsForAllOperationsUnknown() {
        assertThrows(IllegalArgumentException.class, () -> service.getContact("missing"));
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("missing"));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("missing", "A"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("missing", "B"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("missing", "9876543210"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("missing", "Home"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    // An invalid update should leave the old values unchanged.
    @Test
    void rejectsInvalidNameUpdatesNull() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", null));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidNameUpdatesTooLong() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", "ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", "ABCDEFGHIJK"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidPhoneUpdatesNull() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", null));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidPhoneUpdatesEmpty() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", ""));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidPhoneUpdatesTooShort() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123456789"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidPhoneUpdatesTooLong() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "12345678901"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidPhoneUpdatesWithLetter() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123456789a"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidPhoneUpdatesWithHyphen() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123-456789"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidAddressUpdatesNull() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", null));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidAddressUpdatesEmpty() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", ""));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    @Test
    void rejectsInvalidAddressUpdatesTooLong() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", "1234567890123456789012345678901"));
        assertDetails("Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    // Each service should have its own contacts.
    @Test
    void serviceInstancesHaveIndependentStorage() {
        ContactService other = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> other.getContact("1"));
        other.addContact(contact("1"));
        other.deleteContact("1");
        assertEquals("1", service.getContact("1").getContactId());
    }
}