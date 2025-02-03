import java.io.FileWriter;
import java.io.IOException;

public class power{
    public static int naivePower(int x, int n){
        if(x == 0){
            return 0;
        }
        if(n ==0){
            return 1;
        }
        return x * naivePower(x,n-1);
    }


    public static int unoptimizedDCPower(int x, int n){
        if(x == 0 ){
            return 0;
        }
        if( n == 0){
            return 1;
        }
        if (n%2 == 0){
            return unoptimizedDCPower(x, n / 2) * unoptimizedDCPower(x, n / 2);
        }
        return x * unoptimizedDCPower(x, n/2) * unoptimizedDCPower(x, n/2);
    }

    public static int optimizedDCPower(int x, int n){
        if(x==0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        int tempVar = optimizedDCPower(x, n/2);
        if(n%2 == 0){
            return tempVar * tempVar;
        }
        else{
            return x*tempVar*tempVar;
        }
    }

    public static void main(String []args) {
        try (FileWriter csvWriter = new FileWriter("power_measurements.csv")) {
            int staticNval = 30;
            int[] xValues = {2,4,6,8,10,12,14,16,18};
            csvWriter.append("n,x,naive_time,unoptimized_dc_time,optimized_dc_time\n");
            for(int x:xValues){
                long st = System.nanoTime();
                naivePower(x, staticNval);
                long naive_time = System.nanoTime() - st;

                st = System.nanoTime();
                unoptimizedDCPower(x, staticNval);
                long unoptimizedTime  = System.nanoTime() - st;

                st = System.nanoTime();
                optimizedDCPower(x, staticNval);
                long optimizedTime = System.nanoTime() - st;

                csvWriter.append(String.format("%d,%d,%d,%d,%d\n", 
                        staticNval, x, naive_time, unoptimizedTime, optimizedTime));
            }

            int staticXval = 1;
            int[] nValues = {100,200,300,400,500,600,700,800,900,1000,1100};

             for(int n:nValues){
                long st = System.nanoTime();
                naivePower(staticXval, n);
                long naive_time = System.nanoTime() - st;

                st = System.nanoTime();
                unoptimizedDCPower(staticXval, n);
                long unoptimizedTime  = System.nanoTime() - st;

                st = System.nanoTime();
                optimizedDCPower(staticXval, n);
                long optimizedTime = System.nanoTime() - st;

                csvWriter.append(String.format("%d,%d,%d,%d,%d\n", 
                        n, staticXval, naive_time, unoptimizedTime, optimizedTime));
            }

        }
        catch (IOException e) {System.out.println(e);}

    }
}