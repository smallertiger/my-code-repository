package http;

//import sun.rmi.rmic.Generator;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String a;
        String b = "cc";
        a = b;
        System.out.println(a);

        String[] aa = {"aa", "bb", "Cc"};
        Arrays.sort(aa);
        System.out.println(Arrays.toString(aa));
        Arrays.sort(aa, Collections.reverseOrder());
        System.out.println(Arrays.toString(aa));
        Arrays.sort(aa, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(aa));

        String[] hellos = "hello hello".split(" ");

        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
        HashMap map = new HashMap();
        map.put(null, null);// HashMap 允许null值和key
        Float.isNaN(0f);

    }
}
