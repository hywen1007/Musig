import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static Pairing pairing = PairingFactory.getPairing("a.properties");
    static Field G1 = pairing.getG1();
    static Field Zr = pairing.getZr();
    static Element g = G1.newRandomElement();
    static Element p = g.getImmutable();


    public static void main(String[] args) {
        int i = 32; // number of signer
        String m = "hello"; // message

        long allstartTime=System.nanoTime(); //Get start Time
        // generate key
        List<Element> sk = new ArrayList<>();
        List<Element> pk = new ArrayList<>();
        for (int k = 0; k < i; k++) {
            Element x = KeyGen.privateKey(Zr);
            Element X = KeyGen.publicKey(x, g);
            sk.add(x);
            pk.add(X);
        }

        Element[] ai = new Element[i];
        for (int k = 0; k < i; k++) {
            ai[k] = aggreKey.aggregatePar(pk, pk.get(k), Zr);
        }
        Element aggX = aggreKey.aggregateKey(pk, ai, i, g);

        long commitTime=System.nanoTime();
        long signstartTime=System.nanoTime(); //Get start Time
        Element[] ri = new Element[i];
        Element[] Ri = new Element[i];
        for (int k = 0; k < i; k++) {
            ri[k] = commit.commitmentPar(Zr);
        }

        for (int k = 0; k < i; k++) {
            Ri[k] = commit.commitment(ri[k], g);
        }

        Element R = commit.commitR(Ri, g);
        long endcommitTime=System.nanoTime();
        System.out.println("PreCommit+Commit Time：" + (endcommitTime - commitTime));

        //chanllenge
        Element c = challenge.challenge(aggX, R, m, Zr);

        //sign
        Element[] si = new Element[i];
        for (int k = 0; k < i; k++) {
            Element temp = null;
            si[k] = sign.singleSign(ai[k], sk.get(k), c, Zr, temp, ri[k]);
        }
        Element s = sign.sign(si, Zr);
        System.out.println(s.toString().length());
        String sigma = sign.sigma(s, R);
        long signendTime=System.nanoTime();
        System.out.println("Sign Time： "+(signendTime-signstartTime));
        System.out.println("Signature size:" + sigma.length());
        verify.verify(s, R, aggX, c, g);
        long endTime=System.nanoTime();
        System.out.println("verify Time： "+(endTime-signendTime));
        System.out.println("Sign+verify Time： "+(endTime-signstartTime));
        System.out.println("total Time： "+(endTime-allstartTime));
    }
}