
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
          return this.value.toString();
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
      Node p = remove(root,value);
      if (this.root != p) splay(p); 
      if(p!=null)return p.value;
      else return null;
    }
    private boolean contains(Node node, T value) {
        if (node == null) return false;
        if (node.value == value) return true;
        int com = value.compareTo(node.value);
        if (com < 0) return contains(node.left, value);
        else return contains(node.right, value);
    }
    private Node remove(Node node, T value){
        if(node == null) return null;
        if(!contains(root,value)){
            return null;
        }
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
    private void splay(Node p){
      if(p == null || p == this.root) return;
      while(p != this.root){
        Node parent = findParent(p);
        if(parent ==null)break;
        Node grand = findParent(parent);
        if(grand == null){
          rotate(p);
        }
        else if((parent == grand.left && p == parent.left) || 
        (parent == grand.right && p == parent.right)){
          rotate(parent);
          rotate(p);
        }
        else{
          rotate(p);
          rotate(p);
        }
      }
    }
    public void rotate(Node p){
      if(p==null)return;
      Node y = findParent(p);
      if(y==null)return;
      Node z = findParent(y);
      if(z==null){
        this.root=p;
      }
      else{
        relink(z,p,y == z.left);
      }
      if(p==y.left){
        relink(y,p.right,true);
        relink(p,y,false);
      }
      else{
        relink(y,p.left,false);
        relink(p,y,true);
      }
    }
    private void relink (Node parent, Node child, boolean makeLeftChild) {
      if (makeLeftChild){
        parent.left = child;
      }
      else{
        parent.right = child;
      }
    }
    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
}
