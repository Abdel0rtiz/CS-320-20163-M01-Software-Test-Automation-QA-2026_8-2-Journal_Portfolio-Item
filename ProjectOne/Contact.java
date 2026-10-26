/** 3-2 Milestone: Contact Service_Abdel Ortiz_09-20-2026*/


public class Contact {
    // I use final because the ID should not change after the contact is created.
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {
        // The ID cannot be null or longer than 10 characters.
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID");
        }
        this.contactId = contactId;
        // The setters also check the data when creating the contact.
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    // The getters let us read the contact information.
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // I check the first name before saving the change.
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name");
        }
        this.firstName = firstName;
    }

    // The last name has the same 10-character limit as the first name.
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        // This pattern accepts exactly 10 digits, without spaces or hyphens.
        // The phone number is a String to keep any leading zeros.
        if (phone == null || !phone.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("phone must contain exactly 10 digits");
        }
        this.phone = phone;
    }

    // The address cannot be null, empty, or longer than 30 characters.
    public void setAddress(String address) {
        if (address == null || address.isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }
}