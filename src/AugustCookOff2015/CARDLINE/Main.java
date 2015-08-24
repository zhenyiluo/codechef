import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Main
{
    static class Cycle{
        int x;
        int size;
        public Cycle(int x, int size){
            this.x = x;
            this.size = size;
        }
    }
    static boolean[] visited;
    static int[][] dp = new int[2005][2005];
    static int n;
    static int[] posA;
    static int[] posB;
    static int[] A;
    static int[] B;
    static int maxLen;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        initDp();
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();
    }

    private static void initDp(){
        for(int i = 2; i <= 2000; i++){
            for(int j = 1; j <= i; j++){
                if(i - j - 1 >= 0){
                    dp[i][j] = Math.max(dp[i][j], j + dp[i-j-1][j]);
                }
                if(i - j - 2 >= 0){
                    dp[i][j] = Math.max(dp[i][j], j + 1 + dp[i-j-2][j]);
                }
            }
        }
    }

    private static void solve(Scanner sc, PrintWriter pw){
        n = sc.nextInt();
        posA = new int[n+1];
        posB = new int[n+1];
        A = new int[n];
        B = new int[n];
        for(int i = 0; i < n; i++){
            A[i] = sc.nextInt();
            posA[A[i]] = i;
        }
        for(int i = 0; i < n; i++){
            B[i] = sc.nextInt();
            posB[B[i]] = i;
        }
        
        maxLen = 0;
        
        ArrayList<Cycle> cycles = getCycles();
        

        for(int l = 1; l <= n; l++){
            int tot = 0;
            for(int j = 0; j < cycles.size(); j++){
                tot += dp[cycles.get(j).size][l];
                maxLen = Math.max(maxLen, tot);
            }
        }
        pw.println(maxLen);
    }
    
    private static ArrayList<Cycle> getCycles(){
        ArrayList<Cycle> ret = new ArrayList<Cycle>();
        visited = new boolean[n];
        
        for(int i= 0; i < n; i++){
            if(visited[i]){
                continue;
            }
            int p = i;
            int a = A[p];
            int size = 0;
            while(true){
                if(visited[p]){
                    break;
                }
                visited[p] = true;
                ++ size;
                p = posB[a];
                a= A[p];
            }
            ret.add(new Cycle(p, size));
            if(size ==1){
                maxLen++;
            }
        }
        return ret;
    }
}