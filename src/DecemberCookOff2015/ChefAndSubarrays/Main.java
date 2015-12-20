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
        for(int i = 0; i < T; i++){
            int n = sc.nextInt();
            int[] a = new int[n];
            for(int j = 0; j < n; j++){
                a[j] = sc.nextInt();
            }
            pw.println(solve(a, n));
        }
    }

    private static int solve(int[] a, int n){
        int ret = 0;
        int[] preSum = new int[n+1];
        int[] preMul = new int[n+1];
        preMul[0] = 1;
        for(int i = 0; i < n; i++){
            preSum[i+1] = preSum[i] + a[i];
            preMul[i+1] = preMul[i] * a[i];
        }

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(preSum[j+1] - preSum[i] == preMul[j+1] / preMul[i]){
                    ret++;
                }
            }
        }
        return ret;
    }
} 