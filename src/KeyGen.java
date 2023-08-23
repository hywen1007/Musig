import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

public class KeyGen {
    // generate private key
    public static Element privateKey(Field Zr) {
        Element sk = Zr.newRandomElement();
        return sk;
    }

    // generate public key
    public static Element publicKey(Element sk, Element g) {
        Element pk = g.getImmutable().powZn(sk);
        return pk;
    }
}
