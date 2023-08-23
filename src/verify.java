 import it.unisa.dia.gas.jpbc.Element;

public class verify {
    // verify signature
    public static boolean verify(Element s, Element R, Element aggPK, Element c, Element g) {
        Element left = g.getImmutable().powZn(s);
        Element right = R.getImmutable().mul(aggPK.powZn(c));
        return right.isEqual(left);
    }
}

