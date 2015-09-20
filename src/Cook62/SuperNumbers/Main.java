import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        while(sc.hasNext()){
            solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();

    }

    private static void solve(Scanner sc, PrintWriter pw){
        int T = sc.nextInt();
        for(int i = 0; i < T; i++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long ret = solve(a, b);
            pw.println(ret);
        }
    }

    private static long solve(long a, long b){
        long ret = 0;
        for(int x = 1; x <= 60; x++) {
            BigInteger bigInteger = new BigInteger(String.valueOf(2)).pow(x);
            for (int y = 0; Math.pow(2, x) * Math.pow(3, y) <= Math.pow(10, 18); y++) {
                BigInteger bigInteger1 = new BigInteger(String.valueOf(3)).pow(y);
                BigInteger bigInteger2 = new BigInteger(String.valueOf(10)).pow(18);
                BigInteger bigInteger3 = bigInteger.multiply(bigInteger1);
                if(bigInteger3.compareTo(bigInteger2) == 1){
                    break;
                }
                if (bigInteger3.longValue() >= a && bigInteger3.longValue() <= b) {
                    ret++;
                }
            }
        }
        return ret;
    }
}