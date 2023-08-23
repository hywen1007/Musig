import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.security.MessageDigest;

public class hash {
    //hash function:{0,1}->Zp
    public static Element preudoFunc(byte[] bytes, Field Zr) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-512");
            byte[] hash_bytes = hasher.digest(bytes);
            return Zr.newElementFromHash(hash_bytes, 0, hash_bytes.length).getImmutable();
        } catch (Exception e) {
            e.printStackTrace();
            return Zr.newRandomElement();
        }
    }
}