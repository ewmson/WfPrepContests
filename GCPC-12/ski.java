import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Double.max;
import static java.lang.Math.atan;

public class ski {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
         int j = sc.nextInt();
            p = sc.nextInt();
            H = sc.nextInt();
            L = sc.nextInt();
            v0 = Math.sqrt(2*g * (j));

            double time_val = binarySearch(ski::difference, 0.00001, 99999999.0, 0);

            double final_vel = Math.sqrt(2*g*((j+H + p) - slope_height(time_val)));
            double angle = Math.abs(atan(slope_deriv(time_val)) - atan(flight_deriv(time_val))) * 180 / Math.PI;
            System.out.println(time_val +" " +  final_vel + " " +angle);


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
    static double flight_deriv(double time){
       return  -1 * g*time/(v0*v0);
    }
    static double slope_deriv(double time){
        if (time < 0){
            return 0;
        }
        if (time < L/2){
            return -1 * H * 4 * (time / ((L*L)));
        }
        if (time > L){
            return 0;
        }
        return 4 * H * (time / L - 1) / L;
    }
    private static double slope_height(double time) {
        if (time < 0){
            return H;
        }
        if (time < L/2){
            return H * (1- 2*(time/L) * (time/L));
        }
        if (time >= L){
            return 0;
        }
        return 2*H * ((time/L) - 1) * ((time/L) - 1);
    }

    static double flight_height(double t){
        return H + p + -0.5 * g * t*t /(v0*v0) ;
    }
}
