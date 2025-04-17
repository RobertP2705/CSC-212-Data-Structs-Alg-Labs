public class FlipCoin {
    public static boolean FlipHeads(double HeadsChance){
        return Math.random() <= HeadsChance;
    }
    public static int FlipHeadsAll(double HeadsChance, int amount){
        int returnAmount = 0;
        for(int j = 0; j<amount; j++){
            if(FlipHeads(HeadsChance)){
                returnAmount++;
            }
        }
        return returnAmount;
    }
    public static void main(String[] args) {
        int[] n = new int[]{100,200,300,500,1000};
        double[] p = new double[]{.25,.5,.75};
        int[] m = new int[]{10,15};
        System.out.println("Trial #,Probability,Number of Flips,Amount");
        for(int trials : m){
            for(double probability : p){
                for(int amount: n){
                    for(int k = 0; k < trials; k++){
                        int amountOfHeads = FlipHeadsAll(probability, amount);
                        System.out.println(k+","+probability+","+amount+","+amountOfHeads);
                    }
                }
            }
        }   
    }
}