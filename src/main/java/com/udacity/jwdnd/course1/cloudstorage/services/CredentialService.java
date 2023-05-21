package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final EncryptionService encryptionService;

    private final CredentialMapper credentialMapper;

    public CredentialService(EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    public int addCredential(CredentialForm credentialForm) {
        try {
            String encodedKey = generateEncodedKey();
            String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
            Credential credential = new Credential(null, credentialForm.getUrl(), credentialForm.getUsername(), encodedKey, encryptedPassword, credentialForm.getUserid());
            return credentialMapper.insert(credential);
        } catch (Exception ex) {
            return 0;
        }
    }

    private String generateEncodedKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public int updateCredential(CredentialForm credentialForm) {
        try {
            String encodedKey = generateEncodedKey();
            String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
            Credential credential = new Credential(credentialForm.getCredentialid(), credentialForm.getUrl(), credentialForm.getUsername(), encodedKey, encryptedPassword, credentialForm.getUserid());
            return credentialMapper.update(credential);
        } catch (Exception ex) {
            return 0;
        }
    }

    public List<Credential> getUsersCredentials(Integer userid) {
        List<Credential> usersCredentials = credentialMapper.getUsersCredentials(userid);
        usersCredentials.forEach(credential -> credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey())));
        return usersCredentials;
    }

    public int deleteCredentialById(Integer credentialid) {
        return credentialMapper.deleteById(credentialid);
    }
}