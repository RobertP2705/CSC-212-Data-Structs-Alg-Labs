import java.util.ArrayUtils;
public class Shuffle {
    public static int[] Shuffle(int[] n){
        int[] copy = new int[n.length];
        int length = n.length;
        int i;

        while (length > 0){
            i = (int)(Math.random() * array.length);

            if (n[i] != undefined) {
                copy.push(n[i]);
                ArrayUtils.removeElement(n,n[i]);
                length--;
            }
        }

        return copy;
    }
    public static void main(String []args) {
        System.out.println('hello');
        System.out.println(Shuffle([23,2,34,3,5]));
    }
}