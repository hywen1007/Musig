import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

public class sign {
    // single preson signature
    public static Element singleSign(Element aggPar, Element sk, Element c, Field Zr, Element si, Element ri) {
        Element temp = Zr.newRandomElement().setToZero();
        si = ri.add(c.mul(aggPar).mul(sk));
        return si;
    }

    // aggregate signature
    public static Element sign(Element[] si, Field Zr) {
        Element S = Zr.newElement().setToZero();
        for (int i = 0; i < si.length; i++) {
            S = S.getImmutable().add(si[i]);
        }
        return S;
    }
    //sigma
    public static String sigma(Element s, Element R) {
        return (s.toString() + R);
    }
}