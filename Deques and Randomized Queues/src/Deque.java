//import java.lang.Iterable;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item>{
    private node<Item> sentinel;
    private int size;
    //create deque by useing LinkedList with two pointers
    private class node<Item>{
        private node<Item> prev;
        private Item content;
        private node<Item> next;
        private node(node<Item> prev, Item content, node<Item>next){
            this.prev = prev;
            this.content = content;
            this.next = next;
        }
    }

    //helper function
    private void validateArguemnt(Item item){
        if(item == null){
            throw new java.lang.IllegalArgumentException("Invalid argument");
        }
    }

    private void validateSize(int size){
        if(size == 0){
            throw new java.util.NoSuchElementException("Unable to remove");
        }
    }

    // construct an empty deque
    public Deque(){
        this.size = 0;
        this.sentinel = new node<>(sentinel, null, sentinel);
    }

    //is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    //return the number of items on the deque
    public int size(){
        return this.size;
    }

    //add the item to the front
    public void addFirst(Item item){
        validateArguemnt(item);
        if(size == 0) {
            node<Item>first = new node<>(sentinel, item, sentinel);
            sentinel.next = first;
            sentinel.prev = first;
        }else{
            node<Item>originalFirstNode = sentinel.next;
            node<Item> newFirstNode = new node<>(sentinel, item, originalFirstNode);
            originalFirstNode.prev = newFirstNode;
            sentinel.next = newFirstNode;
        }
        size++;

    }

    //add the item to the end
    public void addLast(Item item){
        validateArguemnt(item);
        if(size == 0) {
            node<Item>first = new node<>(sentinel, item, sentinel);
            sentinel.next = first;
            sentinel.prev = first;
        }else{
            node<Item>originalLastNode = sentinel.prev;
            node<Item>newLastNode = new node<>(originalLastNode, item, sentinel);
            originalLastNode.next = newLastNode;
            sentinel.prev = newLastNode;
        }
        size++;
    }

    //remove and return the item from the front
    public Item removeFirst(){
        validateSize(this.size);
        node<Item> firstNode = sentinel.next;
        node<Item> secondFirstNode = sentinel.next.next;
        sentinel.next = secondFirstNode;
        secondFirstNode.prev = sentinel;
        Item content = firstNode.content;
        firstNode.prev = null;
        firstNode.next = null;
        size--;
        return content;
    }

    //remove and return the item from the end
    public Item removeLast(){
        validateSize(this.size);

        node<Item> lastNode = sentinel.prev;
        node<Item> sencondLastNode = lastNode.prev;
        sencondLastNode.next = sentinel;
        sentinel.prev = sencondLastNode;
        Item content = lastNode.content;
        lastNode.prev = null;
        lastNode.next = null;
        size--;
        return content;
    }

    //return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item>{
        //hasNext();
        private node<Item> first = sentinel;
        @Override
        public boolean hasNext(){
//            if(size == 0) {
//                return false;
//            }
            // for empty deque
            if(first.next == null) {
                return false;
            }
            return first.next != sentinel;
        }

        //next()
        @Override
        public Item next(){
            if(!hasNext()){
                throw new java.util.NoSuchElementException("no more items to return");
            }
            first = first.next;
            Item result = first.content;
            return result;
        }

        //remove();
        @Override
        public void remove(){
            throw new java.lang.UnsupportedOperationException("This operation is not supported");
        }

    }

    // unit testing (optional)
    public static void main(String[] args){
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(0);
        dq.addFirst(20);
        dq.addFirst(34);
        dq.addFirst(50);
        dq.addLast(24);    // 50 34 20 10 24
        dq.removeFirst();
        dq.removeFirst();
        dq.removeLast();
        dq.removeLast();
        dq.removeLast();
        System.out.println(dq.size());

        Iterator<Integer> it = dq.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println(it.hasNext());
    }
}
