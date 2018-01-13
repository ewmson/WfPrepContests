/* DOES NOT WORK.... Eric Cannot correctly impleent the spec for some reason */


import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Double.max;

public class ski {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
         int j = sc.nextInt();
            p = sc.nextInt();
            H = sc.nextInt();
            L = sc.nextInt();
            v0 = Math.sqrt(2*g * (j-H));
            for (int ii = 0; ii <= 5; ii++){
                System.err.println(slope_height(ii*10));
            }

            slope_height(40.82482905);
            double time_val = binarySearch(ski::difference, 0.00001, 99999999.0, 0);

            double final_vel = Math.sqrt(2*g*(j-slope_height(time_val)));
            System.out.println(time_val +" " +  final_vel);


        }
    }


    private static double H;
    private static double p;
    private static double v0;
    private static double L;

    static double binarySearch(Function<Double, Double> f, double low, double high,
                               double y) {
        // avoid excessive iterations when root is at 0.0
        while ((high - low) > max(1e-16, 10 * Math.ulp(high))) {
            double mid = (low + high)/2.0;
            double midVal = f.apply(mid);

            if (midVal < y) // or <=, see below.
                low = mid;
            else
                high = mid;
        }
        return (low + high)/2.0;
    }

    static final double g = 9.81;
    static double difference(double time){
        double f = flight_height(time);
        double slope = slope_height(time);
        return slope - f;
    }

    private static double slope_height(double time) {
        if (time < 0){
            return H;
        }
        if (time < L/2){
            return H * (1- 2*(1/time) * (1/time));
        }
        if (time >= L){
            return 0;
        }
        return 2*H * ((1/time) - 1) * ((1/time) - 1);
    }

    static double flight_height(double t){
        return -g/2 * (t/v0) * (t/v0) + H + p;
    }
}
