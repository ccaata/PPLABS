import jdk.dynalink.linker.LinkerServices;
import org.graalvm.polyglot.*;
import java.util.*;

class Polyglot {
    private static String RToUpper(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("R", "toupper(\""+token+"\");");
        String resultString = result.asString();
        polyglot.close();

        return resultString;
    }

    private static int SumCRC(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python", "sum(ord(ch) for ch in '" + token + "')");
        int resultInt = result.asInt();
        polyglot.close();

        return resultInt;
    }

    public static void main(String[] args) {
        Context polyglot = Context.create();
        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\",\"command\",\"jaav\",\"vaaj\",\"ew\"]");//,\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\",\"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");
        Map<Integer, List<String>> crcMap = new HashMap<>();

        for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            crcMap.computeIfAbsent(crc,k -> new ArrayList<>()).add(upper);
        }

        crcMap.forEach((crc,words) -> {
            System.out.println("CRC: " + crc + " -> " + words);
        });

        polyglot.close();
    }
}

