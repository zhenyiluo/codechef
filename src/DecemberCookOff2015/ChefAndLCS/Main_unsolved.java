import java.io.PrintWriter;
import java.util.*;

public class Main_unsolved {
    static int arr[][];

    static void lcs(String s1, String s2, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
            }
        }
    }

    static Set<String> lcs(String s1, String s2, int len1, int len2) {
        if (len1 == 0 || len2 == 0) {
            Set<String> set = new HashSet<>();
            set.add("");
            return set;
        }
        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            Set<String> set = lcs(s1, s2, len1 - 1, len2 - 1);
            Set<String> set1 = new HashSet<>();
            for (String temp : set) {
                temp = temp + s1.charAt(len1 - 1);
                set1.add(temp);
            }
            return set1;
        } else {
            Set<String> set = new HashSet<>();
            Set<String> set1 = new HashSet<>();
            if (arr[len1 - 1][len2] >= arr[len1][len2 - 1]) {
                set = lcs(s1, s2, len1 - 1, len2);
            }
            if (arr[len1][len2 - 1] >= arr[len1 - 1][len2]) {
                set1 = lcs(s1, s2, len1, len2 - 1);
            }
            set1.addAll(set);
            return set1;

        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            String s1 = sc.next();
            String s2 = sc.next();
            arr = new int[n + 1][n + 1];
            lcs(s1, s2, n);
            ArrayList<String> list = new ArrayList<>(lcs(s1, s2, n, n));
            if (list.size() < k) {
                pw.println(-1);
            } else {
                pw.println(findKthInList(list, k-1, 0, list.size() -1));
            }
        }
        sc.close();
        pw.flush();
        pw.close();

    }

    private static String findKthInList(ArrayList<String> list, int k, int start, int end){
        int random = start + (int) (Math.random() * (end - start + 1));
        swap(list, random, end);
        String pivot = list.get(end);
        int left = start;
        int right = end;
        while(true){
            while(list.get(left).compareTo(pivot) < 0 && left < right){
                left ++;
            }

            while(list.get(right).compareTo(pivot) >= 0 && left < right){
                right --;
            }

            if(left == right){
                break;
            }

            swap(list, left, right);
        }

        swap(list, left, end);
        if(k == left){
            return list.get(left);
        }else if(k < left){
            return findKthInList(list, k, start, left-1);
        }else{
            return findKthInList(list, k, left+1, end);
        }
    }

     private static void swap(ArrayList<String> list, int i, int j){
        String tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}