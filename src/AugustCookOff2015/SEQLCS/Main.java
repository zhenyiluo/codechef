import java.io.*;
import java.util.*;
public class Main {
    public static final int MOD = (int)(1e9 + 7);
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int t = sc.nextInt();
        for(int i = 0; i< t; i++){
            solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();
    }
    
    private static void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        int[] current = new int[1 << n];
        int[] next = new int[1 << n];
        int[] qty = new int[k+1];
        for(int i : a){
            qty[i] ++;
        }
        int[][] positions = new int[k+1][];
        for(int i = 1; i <= k; i++){
            positions[i] = new int[qty[i]];
        }
        
        for(int i = 0; i < n; i++){
            positions[a[i]][--qty[a[i]]] = i;
        }
        current[0] = 1;
        for(int i = 0; i < n; i++){
            Arrays.fill(next, 0);
            for(int j = 0; j < (1 << n); j++){
                for(int m = k; m >= 1; m--){
                    int cMask = j;
                    for(int o : positions[m]){
                        int down = cMask >> o;
                        cMask -= Integer.lowestOneBit(down) << o;
                        cMask += 1 << o;
                    }
                    next[cMask] += current[j];
                    if(next[cMask] >= MOD){
                        next[cMask] -= MOD;
                    }
                }
            }
            int[] tmp = current;
            current = next;
            next = tmp;
        }
        long answer = 0;
        for(int i = 0; i < (1 <<n); i++){
            if(Integer.bitCount(i) == l){
                answer += current[i];
            }
        }
        pw.println(answer % MOD);
    }
}