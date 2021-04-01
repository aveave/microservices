package com.encryption;

public class EncryptionService {

    private final String preffix;

    public EncryptionService(String preffix) {
        this.preffix = preffix;
    }

    public String encrypt(String phoneNumber) {
        return phoneNumber + preffix;
    }

    public String decrypt(String encryptedPhoneNumber) {
        return encryptedPhoneNumber.replace(preffix, "");
    }
}
