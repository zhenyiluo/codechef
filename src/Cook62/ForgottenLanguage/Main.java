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
            int n = sc.nextInt();
            int k = sc.nextInt();
            String[] input = new  String[n];
            for(int j = 0; j < n; j++){
                input[j] = sc.next();
            }
            boolean[] mark = new boolean[n];
            for(int j = 0; j < k; j++){
                int num = sc.nextInt();
                for(int l = 0; l < num; l++){
                    String s = sc.next();
                    for(int m = 0; m < n; m++){
                        if(s.equals(input[m])){
                            mark[m] = true;
                        }
                    }
                }
            }
            for(int j = 0; j < n; j++){
                if(mark[j]){
                    pw.print("YES ");
                }else{
                    pw.print("NO ");
                }
            }
            pw.println();
        }
    }

}