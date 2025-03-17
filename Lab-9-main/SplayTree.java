
public class SplayTree<T extends Comparable<T>>{
    public class Node implements PrintableNode{
        public T value;
        public Node left = null;
        public Node right = null;
        public Node(T value) {
            this.value = value;
        }
        @Override
        public PrintableNode getLeft() {
          return this.left;
        }
    
        @Override
        public PrintableNode getRight() {
          return this.right;
        }
    
        @Override
        public String getText() {
          return this.value.toString();
        }
    }
    public Node root;
    public SplayTree() {
        this.root = null;
    }
    public void insert(T value) {
      this.root = insert(this.root, value);
      Node nodeToSplay = get(root, value);
      if (nodeToSplay != null) {
          splay(nodeToSplay);
      }
    }
    private Node insert(Node node, T value) {
        if (node == null) {
          return new Node(value);
        }
    
        int cmp = value.compareTo(node.value);
    
        if (cmp < 0) {
          node.left = insert(node.left, value);
        } else {
          node.right = insert(node.right, value);
        }
        return node;
    }
    private Node findParent(Node p){
      return findParent(this.root,p);
    }
    private Node findParent(Node parent, Node p){
      if (parent == null || parent == p) {
        return null;
      }
      Node left = null;
      if(parent.left == p){
        return parent;
      }
      else if(parent.left != null){
        left = findParent(parent.left,p);
      }
      if(left != null){
        return left;
      }
      else{
        return findParent(parent.right,p);
      }
    }
    public T remove(T value){
        Node nodeToDelete = get(this.root, value);
        if (nodeToDelete == null) {
            return null; 
        }
        T deletedValue = nodeToDelete.value;
        Node parent = findParent(nodeToDelete);
        if (parent != null) {
            splay(parent);
        }
        remove(this.root, value);
        return deletedValue; 
    }
    private Node remove(Node node, T value){
        if(node == null) return null;
        int com = value.compareTo(node.value);
        if(com < 0){
            node.left = remove(node.left,value);
        }
        else if(com > 0){
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
                if(height(node.left) > height(node.right)){
                    T successorValue = findMax(node.left);
                    node.value = successorValue;
                    node.left = remove(node.left,successorValue);
                }
                else{
                    T successorValue = findMin(node.right);
                    node.value = successorValue;
                    node.right = remove(node.right,successorValue);
                }
            }
        }
        return node;
    }
    private int height(Node node) {
      if (node == null) return -1;
      return 1 + Math.max(height(node.left), height(node.right));
    }
    private T findMin(Node node){
        while(node.left != null){
            node = node.left;
        }
        return node.value;
    }
    private T findMax(Node node){
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }
    public T get(T value){
        Node p = get(this.root,value);
        if(p == null){
          return null;
        }
        splay(p);
        return p.value;
    }
    private Node get(Node node, T value) {
      if (node == null) {
          return null;
      }
      
      int compareResult = value.compareTo(node.value);
      
      if (compareResult == 0) {
          return node;
      }
      else if(compareResult < 0){
          return get(node.left, value);
      }
      else{
          return get(node.right, value);
      }
  }
  public void splay(Node p) {
    if (root == null) return;
    if (p == null) return;
    root = splayNode(root, p.value);
  }

  public Node splayNode(Node root, T value) {
    if (root == null) return null;
    
    int cmp = value.compareTo(root.value);
    
    if (cmp == 0) return root;
    
    if (cmp < 0) {
        if (root.left == null) return root;
        
        int leftCmp = value.compareTo(root.left.value);
        
        if (leftCmp < 0 && root.left.left != null) {
            root.left.left = splayNode(root.left.left, value);
            root = rotateRight(root);
            if (root.left == null) return root;
            return rotateRight(root);
        }
        else if (leftCmp > 0 && root.left.right != null) {
            root.left.right = splayNode(root.left.right, value);
            if (root.left.right != null) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        }
        else {
            return rotateRight(root);
        }
    }
    else {
        if (root.right == null) return root;
        
        int rightCmp = value.compareTo(root.right.value);
        
        if (rightCmp > 0 && root.right.right != null) {
            root.right.right = splayNode(root.right.right, value);
            root = rotateLeft(root);
            if (root.right == null) return root;
            return rotateLeft(root);
        }
        else if (rightCmp < 0 && root.right.left != null) {
            root.right.left = splayNode(root.right.left, value);
            if (root.right.left != null) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }
        else {
            return rotateLeft(root);
        }
    }
  }
  private Node rotateRight(Node y) {
    if (y == null || y.left == null) return y;
    
    Node x = y.left;
    y.left = x.right;
    x.right = y;
    
    return x; 
  }

  private Node rotateLeft(Node x) {
      if (x == null || x.right == null) return x;
      
      Node y = x.right;
      x.right = y.left;
      y.left = x;
      
      return y; 
  }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
}
