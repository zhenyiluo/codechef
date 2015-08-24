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
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            String s = sc.next();
            String t = sc.next();
            if(isMatch(s, t)){
                pw.println("Yes");
            }else{
                pw.println("No");                
            }
        }
    }
    
    private static boolean isMatch(String s, String t){
        int len1 = s.length();
        int len2 = t.length();
        if(len1 != len2){
            return false;
        }
        
        for(int i = 0; i < len1; i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(c1 == '?' || c2 == '?'){
                continue;
            }
            if(c1 != c2){
                return false;
            }
        }
        return true;
    }
} 