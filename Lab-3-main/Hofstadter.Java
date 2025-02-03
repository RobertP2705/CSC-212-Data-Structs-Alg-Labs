public class Hofstadter {
    public static int G(int n){
        if(n == 0){
            return 0;
        }
        return n-G(G(n-1));
    }
    public static int optimizedG(int n){
        double tau = (1+Math.sqrt(5))/2;
        return (int)(Math.floor((n+1)*tau) - n - 1.0);
    }
    public static void main(String []args) {
        int num = 100;
        System.out.println(G(num));
        System.out.println(optimizedG(num));
    }
}