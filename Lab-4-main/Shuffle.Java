public class Shuffle {
    public static int[] removeElement(int[] arr, int indexToRemove) {
        if (indexToRemove < 0 || indexToRemove >= arr.length) {
            return arr;  
        }
        
        int[] newArr = new int[arr.length - 1];
        
        for (int i = 0; i < indexToRemove; i++) {
            newArr[i] = arr[i];
        }
        
        for (int i = indexToRemove + 1; i < arr.length; i++) {
            newArr[i - 1] = arr[i];
        }
        
        return newArr;
    }
    public static Integer[] Shuffle_1(Integer[] n) {
        Integer[] copy = new Integer[n.length];
        int remaining = n.length;
        
        while (remaining > 0) {
            int random = (int)(Math.random() * n.length);
            if (n[random] != null) {
                copy[remaining-1] = n[random];
                n[random] = null;
                remaining--;
            }
        }
        return copy;
    }
    public static int[] Shuffle_2(int[] n) {
        int[] copy = new int[n.length];
        int remaining = n.length;
        
        while (remaining > 0) {
                int random = (int)(Math.random() * remaining);
                copy[remaining-1] = n[random];
                n = removeElement(n, random);
                remaining--;
        }
        return copy;
    }
    public static int[] Fisher_Yates(int[] n){
        int m = n.length;
        int t;
        int i;
        while (m > 0){
            i = (int)(Math.random() * m--);
            t = n[m];
            n[m] = n[i];
            n[i] = t;
        }
        return n;
    }
    public static void main(String []args) {
        int[] sizes = {5,10,100,1000};
        int numTrials = 4;

        for (int size : sizes){
            System.out.println("Size: " + size);

            for(int j = 0; j < numTrials; j++){
                System.out.println("Trial: " + j);

                Integer[] arr1 = new Integer[size];
                int[] arr2 = new int[size];
                int[] arr3 = new int[size];
                for(int i = 0;i<size;i++){
                    arr1[i] = i;
                    arr2[i] = i;
                    arr3[i] = i;
                }

                long startTime = System.nanoTime();
                Integer[] result1 = Shuffle_1(arr1);
                long endTime = System.nanoTime();
                System.out.println("Shuffle_1 time: " + (endTime - startTime) / 1000000.0 + " ms");
                System.out.print("Result: ");
                for (int num : result1) System.out.print(num + " ");
                System.out.println();

                startTime = System.nanoTime();
                int[] result2 = Shuffle_2(arr2);
                endTime = System.nanoTime();
                System.out.println("Shuffle_2 time: " + (endTime - startTime) / 1000000.0 + " ms");
                System.out.print("Result: ");
                for (int num : result2) System.out.print(num + " ");
                System.out.println();

                startTime = System.nanoTime();
                int[] result3 = Fisher_Yates(arr3);
                endTime = System.nanoTime();
                System.out.println("Fisher Yates time: " + (endTime - startTime) / 1000000.0 + " ms");
                System.out.print("Result: ");
                for (int num : result3) System.out.print(num + " ");
                System.out.println();
            }
        }
    }
}