import java.io.*;
import java.util.*;
public class Main {
    public static final int MOD = (int)(1e9 + 7);
    static int n;
    static int[][] memo;
    static int[][] newMask;
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
        n = sc.nextInt();
        int K = sc.nextInt();
        int L = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        newMask = new int[18][1 << n];
        memo = new int[18][1 << n];
        for(int i = 1; i <= K; i++){
            for(int j = 0; j < (1 << n); j++){
                newMask[i][j] = nextMask(i, j, a);
            }
        }
        for(int i = 0; i < memo.length; i++){
            Arrays.fill(memo[i], -1);
        }
        pw.println(solve(1, 0, K, L));
    }
    
    private static int solve(int b, int mask, int K, int L){
        int ret = 0;
        if(b > n){
            return Integer.bitCount(mask) == L ? 1:0;
        }
        if(memo[b][mask] != -1){
            return memo[b][mask];
        }
        for(int i = 1; i <= K; i++){
            ret += solve(b+1, newMask[i][mask], K, L);
            if(ret >= MOD){
                ret %= MOD;
            }
        }
        return memo[b][mask] = ret;
    }
    
    private static int nextMask(int v, int mask, int[] a){
        int n = a.length;
        int ret = 0;
        int[][] lcs = new int[2][20];
        int len = 0;
        for(int i = 0; i < n; i++){
            if((mask & (1 << i))!= 0){
                ++len;
            }
            lcs[0][i+1] = len;
        }
        
        for(int i = 1; i <= n; i++){
            lcs[1][i] = v == a[i-1] ? 1 + lcs[0][i-1] : Math.max(lcs[0][i], lcs[1][i-1]);
            if(lcs[1][i] > lcs[1][i-1]){
                ret |= (1 <<(i-1));
            }
        }
        return ret;
    }
    
}