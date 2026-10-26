/** 3-2 Milestone: Contact Service_Abdel Ortiz_09-20-2026*/

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContactTest {
    // I use this valid contact as a starting point for the tests.
    private Contact contact() {
        return new Contact("1", "Abdel", "Ortiz", "0123456789", "123 Main St");
    }

    // I check that the constructor stores the values it receives.
    @Test
    void constructorStoresAllFields() {
        Contact contact = contact();
        assertEquals("1", contact.getContactId());
        assertEquals("Abdel", contact.getFirstName());
        assertEquals("Ortiz", contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    // Values right at the allowed limits should be accepted.
    @Test
    void acceptsMaximumLengths() {
        Contact contact = new Contact("1234567890", "ABCDEFGHIJ", "ABCDEFGHIJ",
                "0123456789", "123456789012345678901234567890");
        assertEquals(10, contact.getContactId().length());
        assertEquals(10, contact.getFirstName().length());
        assertEquals(10, contact.getLastName().length());
        assertEquals(30, contact.getAddress().length());
    }

    // assertThrows checks that invalid data causes the expected exception.
    @Test
    void rejectsInvalidIdNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "A", "B", "0123456789", "Home"));
    }

    @Test
    void rejectsInvalidIdTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "A", "B", "0123456789", "Home"));
    }

    // I test invalid names when creating and updating a contact.
    // The old value should still be stored after the error.
    @Test
    void rejectsInvalidFirstNameInConstructorAndSetterNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "B", "0123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertEquals("Abdel", contact.getFirstName());
    }

    @Test
    void rejectsInvalidFirstNameInConstructorAndSetterTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "ABCDEFGHIJK", "B", "0123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("ABCDEFGHIJK"));
        assertEquals("Abdel", contact.getFirstName());
    }

    @Test
    void rejectsInvalidLastNameInConstructorAndSetterNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", null, "0123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertEquals("Ortiz", contact.getLastName());
    }

    @Test
    void rejectsInvalidLastNameInConstructorAndSetterTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "ABCDEFGHIJK", "0123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("ABCDEFGHIJK"));
        assertEquals("Ortiz", contact.getLastName());
    }

    // These tests check null, empty, and incorrectly formatted phone numbers.
    @Test
    void rejectsInvalidPhoneInConstructorAndSetterNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", null, "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(""));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterTooShort() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123456789"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "12345678901", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345678901"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterWithLetter() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "123456789a", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123456789a"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterWithHyphen() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "123-456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123-456789"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterWithSpace() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "123 456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123 456789"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterWithPlusSign() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "+123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("+123456789"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterWithLeadingSpace() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", " 123456789", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(" 123456789"));
        assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void rejectsInvalidPhoneInConstructorAndSetterWithNonAsciiDigits() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "１２３４５６７８９０", "Home"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("１２３４５６７８９０"));
        assertEquals("0123456789", contact.getPhone());
    }

    // I check the values that are not allowed for an address.
    @Test
    void rejectsInvalidAddressInConstructorAndSetterNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "0123456789", null));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    void rejectsInvalidAddressInConstructorAndSetterEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "0123456789", ""));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(""));
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    void rejectsInvalidAddressInConstructorAndSetterTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "A", "B", "0123456789", "1234567890123456789012345678901"));
        Contact contact = contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("1234567890123456789012345678901"));
        assertEquals("123 Main St", contact.getAddress());
    }

    // I update the fields with valid values and check that the ID stays the same.
    @Test
    void settersAcceptBoundaryValuesWithoutChangingId() {
        Contact contact = contact();
        contact.setFirstName("ABCDEFGHIJ");
        contact.setLastName("KLMNOPQRST");
        contact.setPhone("0000000000");
        contact.setAddress("123456789012345678901234567890");
        assertEquals("ABCDEFGHIJ", contact.getFirstName());
        assertEquals("KLMNOPQRST", contact.getLastName());
        assertEquals("0000000000", contact.getPhone());
        assertEquals("123456789012345678901234567890", contact.getAddress());
        assertEquals("1", contact.getContactId());
    }

}