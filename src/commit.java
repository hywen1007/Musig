import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

public class commit {
    //compute ri
    public static Element commitmentPar(Field Zr) {
        Element ri = Zr.newRandomElement();
        return ri;
    }

    //compute Ri
    public static Element commitment(Element ri, Element g) {
        Element Ri = g.getImmutable().powZn(ri);
        return Ri;
    }

    //compute R
    public static Element commitR(Element[] Ri, Element g) {
        Element R = g.duplicate().setToOne();
        for (int i = 0; i < Ri.length; i++) {
            R = R.mul(Ri[i]);
        }
        return R;
    }
}
