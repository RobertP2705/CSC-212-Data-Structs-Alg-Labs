public class SplayTree<T extends Comparable<T>>{
    public class Node implements PrintableNode{
        public T value;
        public Node left, right;
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
          return Integer.toString(this.value);
        }
    }
    public Node root;
    public SplayTree() {
        this.root = null;
    }
    public void insert(T value) {
      Node addedNode = new Node(value);
      this.root = insert(this.root, value, addedNode);
      splay(addedNode);
    }
    private Node insert(Node node, T value, Node addedNode) {
        if (node == null) {
          return addedNode;
        }
    
        int cmp = value.compareTo(node.value);
    
        if (cmp < 0) {
          node.left = insert(node.left, value, addedNode);
        } else {
          node.right = insert(node.right, value, addedNode);
        }
        return node;
    }
    private Node findParent(Node p){
      return findParent(this.root,p)
    }
    private Node findParent(Node parent, Node p){
      if (parent == null || parent == p) {
        return null;
      }
      Node left = null;
      if(parent.left == p){
        return parent
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
    // private void splay(Position<Entry<K,V>> p) {
    //     while (!isRoot(p)) {
    //         Position<Entry<K,V>> parent = parent(p);
    //         Position<Entry<K,V>> grand = parent(parent);
    //         if (grand == null)                                          // zig case
    //             rotate(p);
    //         else if ((parent == left(grand)) == (p == left(parent))) {  // zig-zig case
    //             rotate(parent);      // move PARENT upward
    //             rotate(p);           // then move p upward
    //         } else {                                                    // zig-zag case
    //             rotate(p);           // move p upward
    //             rotate(p);           // move p upward again
    //         }
    //     }
    // }
  
    private Node splay(Node p){
      while(p != this.root){
        Node parent = findParent(p);
        Node grand = findParent(parent);
        if(grand == null){
          rotate(p);
        }
        else if((parent == grand.left)){
          rotate(parent);
          rotate(p);
        }
        else{
          rotate(p);
          rotate(p);
        }
      }
    }
    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
}
