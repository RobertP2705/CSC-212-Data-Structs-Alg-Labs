public class CircularLinkedList<T> {
    private static class Node<T>{
        private T data;
        private Node<T> next;

        private Node(T data){
            this.data = data;
            this.next = null;
        }
    }
    private Node<T> head;
    private Node<T> currentNode;
    public CircularLinkedList(){
        this.head = null;
        this.currentNode = null;
    }
    public void append(T data){
        Node<T> newNode = new Node<T>(data);
        if(this.head == null){
            this.head = newNode;
            this.currentNode = newNode;
            this.head.next = this.head;
        }
        else{
            Node<T> temp = this.head;
            while(temp.next != this.head){
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = this.head;
        }
    }
    public T getCurrentNode() {
        if(this.currentNode != null) {
            return this.currentNode.data;
        }
        return null;
    }
    public void step(){
        if(this.head != null){
            this.currentNode = this.currentNode.next;
        }
    }
}
