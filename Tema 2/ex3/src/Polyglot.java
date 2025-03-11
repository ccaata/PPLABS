import jdk.dynalink.linker.LinkerServices;
import org.graalvm.polyglot.*;
import java.util.*;

class Polyglot {
    private static double calc_prob_R(int nr_aruncari, int x){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        String R_Script = "pbinom(" + x + ", size=" + nr_aruncari + ", prob=0.5)";

        Value result = polyglot.eval("R", R_Script);
        double result_double = result.asDouble();
        polyglot.close();

        return result_double;
    }

    private static int citeste_nr_aruncari(){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        String py_script = "n = int(input('Numarul de aruncari: '))\n" + "n";

        Value result = polyglot.eval("python", py_script);
        int nr_aruncari = result.asInt();
        polyglot.close();

        return nr_aruncari;
    }

    private static int citeste_x(){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        String py_script = "n = int(input('x : '))\n" + "n";

        Value result = polyglot.eval("python", py_script);
        int x = result.asInt();
        polyglot.close();

        return x;
    }


    public static void main(String[] args) {

        int nr_aruncari =  citeste_nr_aruncari();

        int x = citeste_x();

        while(x < 1 || x > nr_aruncari)
        {
            System.out.println("EROARE: x trebuie să fie între 1 și " + nr_aruncari);
            x = citeste_x();
        }

        double probabilitate = calc_prob_R(nr_aruncari, x);

        System.out.printf("Probabilitatea de a obține cel mult %d pajură: %.4f", x, probabilitate);

    }
}

