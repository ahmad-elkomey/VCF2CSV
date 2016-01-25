/*
 * 
 */
package vcf2csv;

import java.util.LinkedList;

/**
 * A class representing a phone contact with a name and one or more phone
 * numbers.
 *
 * @author Ahmad
 */
public class Contact {

    private String name = "";
    private LinkedList<String> phones = new LinkedList<>();

    public Contact() {
    }

    public Contact(String name) {
        this.setName(name);
    }

    /**
     * Appends a phone number to the list of phone numbers related to the
     * contact.
     *
     * @param phone String holding the phone number.
     */
    public void addPhone(String phone) {
        phones.add(phone);
    }

    /**
     * Sets the name of the contact.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returns the string holding the name as is.
     */
    public String getName() {
        return name;
    }

    /**
     * returns a String holding the full name of the contact with the semi-colon
     * replaced by a space.
     *
     * @return string holding the contact name with the semi-colon replaced with
     * a space.
     */
    public String getFullName() {
        String[] splitted = name.split(";");
        return String.join(" ", splitted);
    }

    /**
     * returns a String holding the first name and last name reversed with the
     * semi-colon replaced with a space.
     *
     * @return returns a string holding the contact name reversed.
     */
    public String getFullNameReversed() {
        String[] splitted = name.split(";");
        if (splitted.length == 1) {
            return name;
        }
        return splitted[1] + " " + splitted[0];
    }

    /**
     * @return returns array of strings of the contact phone numbers.
     */
    public String[] getPhones() {
        return phones.toArray(new String[phones.size()]);
    }
}
