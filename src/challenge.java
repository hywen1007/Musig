import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;


public class challenge {
    // compute challenge
    public static Element challenge(Element aggPK, Element R, String m, Field Zr) {
        byte[] temp = (aggPK.toString() + R + m).getBytes();
        Element c = hash.preudoFunc(temp, Zr);
        return c;
    }
}
