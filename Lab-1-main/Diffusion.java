class Diffusion{
   public static void main(String []args) {
      int trials = 10;
      int[] steps = {10,20,30,300,4000,50000,100000};
      for(int i = 0; i < steps.length; i++){
         for(int j = 1; j<=trials;j++){
            Particle a = new Particle();
            a.moveSteps(steps[i]);
            System.out.println("Steps:" + steps[i] + " | Trial:" + j + " | Position: " + a.getPosition());
         }
      }
   }
}