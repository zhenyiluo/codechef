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
            int[] a= new int[n];
            for(int j = 0; j < n; j++){
                a[j] = sc.nextInt();
            }
            ArrayList<Integer> ret = solve(n, a);
            pw.print(ret.size()+ " ");
            for(int num : ret){
                pw.print(num + " ");
            }
            pw.println();
        }
    }

    private static ArrayList<Integer> solve(int n, int[] a){
        ArrayList<Integer> ret = new ArrayList<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i = 0; i < n; i++){
            if(tm.higherKey(a[i]) != null){
                int key = tm.higherKey(a[i]);
                if(tm.get(key) == 1){
                    tm.remove(key);
                }else{
                    tm.put(key, tm.get(key) -1);
                }
            }
            if(tm.containsKey(a[i])){
                tm.put(a[i], tm.get(a[i]) + 1);
            }else{
                tm.put(a[i], 1);
            }
        }
        for(int val : tm.keySet()){
            int num = tm.get(val);
            for(int i = 0; i < num; i++){
                ret.add(val);
            }
        }
        return ret;
    }
}