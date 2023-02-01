package br.ufsm.politecnico.csi.tapw.pila.util;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CriaChave {

    @SneakyThrows
    public static void main(String[] args) {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);

        SecureRandom rnd = new SecureRandom();

        KeyPair kp = kpg.generateKeyPair();


        try (PrintWriter out = new PrintWriter("private.key")) {
            out.println(kp.getPrivate());
        }

        try (PrintWriter out = new PrintWriter("public.key")) {
            out.println(kp.getPublic());
        }
    }

    public KeyPair LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        // Read Public Key.
        File filePublicKey = new File(path + "/public.key");
        FileInputStream fis = new FileInputStream(path + "/public.key");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();

        // Read Private Key.
        File filePrivateKey = new File(path + "/private.key");
        fis = new FileInputStream(path + "/private.key");
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();

//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//        kpg.generateKeyPair(encodedPrivateKey, e);


        // Generate KeyPair.
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PKCS8EncodedKeySpec  publicKeySpec = new PKCS8EncodedKeySpec (
                encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        X509EncodedKeySpec privateKeySpec = new X509EncodedKeySpec (
                encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }
}

