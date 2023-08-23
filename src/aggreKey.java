import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

import java.util.List;

public class aggreKey {
    //ai
    public static Element aggregatePar(List<Element> PK, Element pk, Field Zr) {
        Element aggrePar = null;
        byte[] temp = (pk.toString() + PK).getBytes();
        aggrePar = hash.preudoFunc(temp, Zr);
        return aggrePar;
    }


    // aggreate public key
    public static Element aggregateKey(List<Element> PK, Element[] aggrePar, int i, Element g) {
        Element aggreKey = g.duplicate().setToOne();
        for (int j = 0; j < i; j++) {
            aggreKey = aggreKey.getImmutable().mul(PK.get(j).powZn(aggrePar[j]));
        }
        return aggreKey;
    }
}
