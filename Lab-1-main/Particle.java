
public class Particle {
    private int position;
    public Particle(){position = 0;}
    public Particle(int initPos){position = initPos;}
    public void incrementPos(){position++;}
    public void decrementPos(){position--;}
    public void moveSteps(int steps){
        for(int i = 0; i<steps; i++){
            int random = (int)(Math.random() + 0.5);
            if(random == 0){
                incrementPos();
            }
            else{
                decrementPos();
            }
        }
    }
    public int getPosition(){return position;}
}