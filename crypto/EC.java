package com.company.crypto;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.math.ec.ECPoint;


public class EC {
    X9ECParameters ecp;
    ECDomainParameters domainParams;
    AsymmetricCipherKeyPair keyPair;
    ECKeyGenerationParameters keyGenParams;
    ECKeyPairGenerator generator;
    byte[] privateKeyBytes;
    ECPoint Q;

    ECPrivateKeyParameters privateKey;
    ECPublicKeyParameters publicKey;

    String privKey;
    String pubKey;

    public EC(){
        // Get domain parameters for example curve secp256r1
        ecp = SECNamedCurves.getByName("secp256r1");
        domainParams = new ECDomainParameters(ecp.getCurve(),
                ecp.getG(), ecp.getN(), ecp.getH(),
                ecp.getSeed());
        keyGenParams = new ECKeyGenerationParameters(domainParams, new SecureRandom());
        generator = new ECKeyPairGenerator();


    }

    public void gen() {
        generator.init(keyGenParams);
        keyPair = generator.generateKeyPair();
        privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        publicKey = (ECPublicKeyParameters) keyPair.getPublic();
        privateKeyBytes = privateKey.getD().toByteArray();

        pubKey = toHex(privateKeyBytes);
        privKey = toHex(publicKey.getQ().getEncoded(true));
    }

    public String calc(){
        // calculate the public key only using domainParams.getG() and private key
        Q = domainParams.getG().multiply(new BigInteger(privateKeyBytes));
        return toHex(Q.getEncoded(true));
    }

    public boolean keyMatch(){
        // The calculated public key and generated public key should always match
        return (toHex(publicKey.getQ().getEncoded(true)).equals(toHex(Q.getEncoded(true))));
    }

    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b: data) {
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }

    public String getPrivKey() {
        return privKey;
    }

    public String getPubKey() {
        return pubKey;
    }
}
