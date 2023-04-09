package io.github.toms74209200.domain;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public record Email(InternetAddress mail) {

    public Email {
        try {
            mail.validate();
        } catch (AddressException e) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public static Email of(String mail) {
        try {
            InternetAddress internetAddress = new InternetAddress(mail);
            internetAddress.validate();
            return new Email(internetAddress);
        } catch (AddressException e) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
