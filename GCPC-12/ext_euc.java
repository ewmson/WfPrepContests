import java.util.Arrays;
import java.util.Scanner;

public class ext_euc {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t ;i++){
            long k = sc.nextInt();
            long c = sc.nextInt();
            if (c == 1){
                long ans = k +1;
                if (ans > 1000000000L){
                    System.out.println("IMPOSSIBLE");
                }
                else {
                    System.out.println(ans);
                }
                continue;
            }
            long[] output = solveDiophantine(-k, c, 1);
            if (output == null){
                System.out.println("IMPOSSIBLE");
            }
            else {
                if (output[1] <= 0){
                    long m = (output[1]/ (-k/output[2]));
                    if (output[1] + m * (-k/output[2]) <= 0){
                        m +=1;
                    }
                    System.out.println(output[1] + m * (-k/output[2]));
                }
                else if (output[1] > 1000000000L){
                    long m = (output[1]/ (-k/output[2]));
                    if (output[1] + m * (-k/output[2]) > 1000000000L){
                        m -=1;
                    }
                    System.out.println(output[1] + m * (-k/output[2]));
                }
                else {
                    System.out.println(output[1]);
                }
            }
        }

    }

    private static long[] solveDiophantine(long  a, long  b, long c) {

        long[] e = extEuclid(a, b);

        long  k = c / e[2];

        if (c - k * e[2] != 0)
            return null;

        return new long[]{e[0] * k, e[1] * k, e[2]};
    }

    private static long[] extEuclid(long a, long b) {

        long
                s0 = 1, s1 = 0, sTemp;

        long
                t0 = 0, t1 = 1, tTemp;
        long
                r0 = a, r1 = b, rTemp;

        long
                q;


        while
                (r1 != 0) {

            q = r0 / r1;


            rTemp = r1;

            r1 = r0 - q
                    *
                    r1;

            r0 = rTemp;


            sTemp = s1;

            s1 = s0 - q
                    *
                    s1;

            s0 = sTemp;


            tTemp = t1;

            t1 = t0 - q
                    *
                    t1;

            t0 = tTemp;

        }


        long
                [] output = {s0, t0, r0};

        return
                output;

    }
}
