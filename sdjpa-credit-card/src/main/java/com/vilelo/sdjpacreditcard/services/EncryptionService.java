package com.vilelo.sdjpacreditcard.services;

public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);

}
