import java.util.*;
import java.io.*;
public class Main {

    private static final long MOD = (long)Math.pow(2, 32);
    static int[] arr;
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
        int Q = sc.nextInt();
        long S = sc.nextLong();
        long A = sc.nextLong();
        long B = sc.nextLong();
        pw.println(solve(Q, S, A, B));
    }

    private static long solve(int Q, long S, long A, long B){
        arr = new int[Integer.MAX_VALUE/32 +1];
        for(int i = 0; i < Q; i++){
            if(S % 2 != 0){
                int val = (int)(S / 2);
                int x = val / 32;
                int y = val % 32;
                int num = arr[x];
                if(((num >> y) & 1) == 0 ){
                    arr[x] |= (1 << y);
                }
            }else{
                int val = (int)(S / 2);
                int x = val / 32;
                int y = val % 32;
                int num = arr[x];
                if(((num >> y) & 1) != 0 ){
                    arr[x] &= ~(1 << y);
                }
            }

            S = (A * S + B) % MOD;
        }
        long ret = 0;
        for(int i = 0; i <= Integer.MAX_VALUE/32; i++){
            if(arr[i] != 0){
                for(int j = 0; j < 32; j++){
                    if(((arr[i] >> j) & 1) != 0){
                        ret += i * 32 + j;
                    }
                }
            }
        }
        return ret;
    }


} 