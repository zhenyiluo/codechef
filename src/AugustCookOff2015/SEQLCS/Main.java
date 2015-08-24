import java.io.*;
import java.util.*;
public class Main {
    public static final int MOD = (int)(1e9 + 7);
    static int n;
    static int K; 
    static int L;
    static int dp[][];
    static int nextMask[][];
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
        K = sc.nextInt();
        L = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        dp = new int[n+2][1 << n];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        
        nextMask = new int[1 << n][K+1];
        
        for(int i = 0; i < (1 << n); i++){
            for(int j = 1; j <= K; j++){
                nextMask[i][j] = calcNextMask(i, j, a);
            }
        }
        pw.println(solve(1, 0));
    }
    
    private static int calcNextMask(int mask, int v, int[] a){
        int ret = 0;
        int[][] lcs = new int[2][n+1];
        int count = 0;
        for(int i = 0; i < n; i++){
            if((mask & (1 << i)) != 0){
                count++;
            }
            lcs[0][i+1] = count;
        }
        
        for(int i = 1; i <= n; i++){
            if(a[i-1] == v){
                lcs[1][i] = lcs[0][i-1] + 1;
            }else{
                lcs[1][i] = Math.max(lcs[0][i], lcs[1][i-1]);
            }
            if(lcs[1][i] > lcs[1][i-1]){
                ret |= (1 << (i-1));
            }
        }
        
        return ret;
    }
    
    private static int solve(int b, int mask){
        if(dp[b][mask] != -1){
            return dp[b][mask];
        }
        
        if(b > n){
            return dp[b][mask] = (Integer.bitCount(mask) == L ? 1: 0);
        }
        
        int ret = 0;
        for(int i = 1; i <= K; i++){
            ret += solve(b+1, nextMask[mask][i]) % MOD;
            ret %= MOD;
        }
        
        return dp[b][mask] = ret;
    }
}