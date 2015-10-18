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
            int n = sc.nextInt();
            String[] s = new String[n];
            int minLen = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++){
                s[j] = sc.next();
                minLen = Math.min(minLen, s[j].length());
            }

            pw.println(getWordStem(s, minLen, n));
        }
    }

    private static String getWordStem(String[] s, int minLen, int n){
        if(minLen == 0){
            return "";
        }
        ArrayList<String> ret = new ArrayList<>();
        for(int len = 1; len <= minLen; len++){
            for(int start = 0; start < s[0].length() - len + 1; start++){
                String tmp = s[0].substring(start, start + len);
                boolean flag = true;
                for(int i = 1; i < n; i++){
                    if(!s[i].contains(tmp)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    if(!ret.isEmpty() && ret.get(ret.size()-1).length() < tmp.length()){
                        ret = new ArrayList<>();
                    }
                    ret.add(tmp);
                }
            }
        }
        Collections.sort(ret);
        return ret.get(0);
    }
} 