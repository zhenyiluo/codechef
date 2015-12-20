import java.io.PrintWriter;
import java.util.*;

public class Main {

    static final long INF = 10L * Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        while (sc.hasNext()) {
           solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();

    }

    private static int[][] next(String a){
        int n = a.length();
        int[][] ret = new int[n+1][26];

        Arrays.fill(ret[n], n);
        for(int i = n-1; i>= 0; i--){
            ret[i] = ret[i+1].clone();
            ret[i][a.charAt(i) - 'a'] = i;
        }
        return ret;
    }

    private static void solve(Scanner sc, PrintWriter pw) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        String a = sc.next();
        String b = sc.next();

        int[][] dp = new int[n+1][n+1];
        long[][] cnt = new long[n+1][n+1];


        for(int i = 0; i <= n; i++){
            Arrays.fill(cnt[i], 1);
        }

        int[][] nextA = next(a);
        int[][] nextB = next(b);

        for(int i = n-1; i>= 0; i--){
            for(int j = n -1; j >= 0; j--){
                for(int c = 0;  c < 26; c++){
                    int i1 = nextA[i][c];
                    int j1 = nextB[j][c];
                    if(i1 == n || j1 == n){
                        continue;
                    }

                    if(dp[i1+1][j1+1] + 1 > dp[i][j]){
                        dp[i][j] = dp[i1+1][j1+1] + 1;
                        cnt[i][j] = cnt[i1+1][j1+1];
                    }else if(dp[i1+1][j1+1] +1 ==dp[i][j]){
                        cnt[i][j] = Math.min(cnt[i][j]+ cnt[i1+1][j1+1], INF);
                    }
                }
            }
        }

        if(cnt[0][0] < k){
            pw.println(-1);
        }else{
            int i = 0;
            int j = 0;
            for(int pos = 0; pos < dp[0][0]; pos++){
                for(int c = 0; c < 26; c++){
                    int i1 = nextA[i][c];
                    int j1 = nextB[j][c];
                    if(i1 == n || j1 == n){
                        continue;
                    }
                    if(dp[i1+1][j1+1] +1 == dp[i][j]){
                        if(cnt[i1+1][j1+1] >= k){
                            pw.print((char) ('a' + c));
                            i = i1+1;
                            j = j1+1;
                            break;
                        }else{
                            k -= cnt[i1+1][j1+1];
                        }
                    }
                }
            }
            pw.println();
        }

    }


}