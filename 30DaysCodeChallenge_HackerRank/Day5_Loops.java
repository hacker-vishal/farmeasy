import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

        static void TableOf2(int n)
        {
            for(int i=1;i<=10;i++)
            {
                int res = n*i;
                System.out.println(n+" x "+i+" = "+res);
            }
        }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        TableOf2(n);
        scanner.close();
    }
}
