import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        solve(sc, pw);
        sc.close();
        pw.flush();
        pw.close();
    }
    
    private static void solve(Scanner sc, PrintWriter pw){
        int t = sc.nextInt();
        for(int i = 0; i < t; i++){
            long n = sc.nextLong();
            if((n & n +1) != 0){
                pw.println(-1);
            }else{
                if(n == 1){
                    pw.println(2);
                }else{
                    pw.println(n >> 1);
                }
            }            
        }
    }
    
} 