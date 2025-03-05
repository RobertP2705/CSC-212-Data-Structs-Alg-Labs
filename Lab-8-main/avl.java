public class avl {
    /* Node Class and AVL tree implementation
    Inspired by:
    https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/datastructures/balancedtree/AVLTreeRecursive.java */
    public class Node {
        public int bf;
        public int value;
        public int height;
        public Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    public avl() {
        this.root = null;
    }
    public Node root;
    public int height(Node node){
        if (node == null) return -1;
        return node.height;
    }

    private boolean contains(Node node, int value) {
        if (node == null) return false;
        if (node.value == value) return true;
        if (value < node.value) return contains(node.left, value);
        else return contains(node.right, value);
    }

    public void insert(int value) {
        insert(root, value);
    }
    private Node insert(Node node, int value) {
        if (node == null) return new Node(value);
    
        int cmp = node.value;
    
        if (value < cmp) {
          node.left = insert(node.left, value);
        } else {
          node.right = insert(node.right, value);
        }
        update(node);
        return balance(node);
    }

    private void update(Node node){
        if(node == null) return;
        int leftNodeHeight = height(node.left);
        int rightNodeHeight = height(node.right);
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.bf = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node){
        if(node.bf == -2){
            if(node.left.bf <= 0){
                return leftLeftCase(node);
            } else {
                return leftRightCase(node);
            }
        } else if(node.bf == 2){
            if(node.right.bf >= 0){
                return rightRightCase(node);
            } else {
                return rightLeftCase(node);
            }
        }
        return node;
    }

    private Node leftLeftCase(Node node){
        return rightRotation(node);
    }

    private Node leftRightCase(Node node){
        node.left = leftRotation(node.left);
        return leftLeftCase(node);
    }

    private Node rightRightCase(Node node){
        return leftRotation(node);
    }

    private Node rightLeftCase(Node node){
        node.right = rightRotation(node.right);
        return rightRightCase(node);
    }

    private Node leftRotation(Node node){
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private Node rightRotation(Node node){
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }
    public void remove(int value){
        remove(root,value);
    }
    private Node remove(Node node, int value){
        if(node == null) return null;
        if(!contains(root,value)){
            return null;
        }
        int checkNum = node.value;
        if(value < checkNum){
            node.left = remove(node.left,value);
        }
        else if(value > checkNum){
            node.right = remove(node.right,value);
        }
        else{
            if(node.left == null){
                return node.right;
            }
            else if(node.right == null){
                return node.left;
            }
            else{
                if(node.left.height > node.right.height){
                    int successorValue = findMax(node.left);
                    node.value = successorValue;
                    node.left = remove(node.left,successorValue);
                }
                else{
                    int successorValue = findMin(node.right);
                    node.value = successorValue;
                    node.right = remove(node.right,successorValue);
                }
            }
        }

        update(node);
        return balance(node);
    }
    private int findMin(Node node){
        while(node.left != null){
            node = node.left;
        }
        return node.value;
    }
    private int findMax(Node node){
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }
}
