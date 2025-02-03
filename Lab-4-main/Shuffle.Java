import java.util.Arrays;
import java.util.ArrayUtils;
public class Shuffle {
    public static int[] Shuffle(int[] n){
        int[] copy = [];
        int length = n.length;
        int i;

        while (length){
            i = Math.floor(Math.random() * array.length);

            if (Arrays.asList(n).contains(i)) {
                copy.push(n[i]);
                ArrayUtils.removeElement(n,n[i]);
                length--;
            }
        }

        return copy;
    }
    public static void main(String []args) {

    }
}