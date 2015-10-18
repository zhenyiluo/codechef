import java.io.*;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(System.out);
        solve(pw);
        pw.flush();
        pw.close();
    }

    private static void solve(PrintWriter pw) throws IOException {
        StreamTokenizer st = new StreamTokenizer(System.in);
        st.nextToken();
        int t = (int) st.nval;
        for(int i = 0; i < t; i++){
            st.nextToken();
            int n = (int) st.nval;
            int[] nums = new int[n];
            boolean flag = true;
            for(int j = 0; j< n; j++){
                st.nextToken();
                nums[j] = (int) st.nval;
                if(j > 1){
                    if(nums[j] < nums[j-2]){
                        flag = false;
                    }
                }
            }
            if(flag){
                pw.println("YES");
            }else{
                pw.println("NO");
            }
        }
    }

}