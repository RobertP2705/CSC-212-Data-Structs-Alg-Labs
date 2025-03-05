public class driver {
    public static void main(String[] args) {
        avl avltree = new avl();
        for (int i = 1; i < 10; i++) {
            avltree.insert(i);
        }
        System.out.println(avltree);
        String serial = avltree.inOrderSerialize();
        System.out.println(serial);
    }
}
