import java.lang.Iterable;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item>{
    private node sentinel;
    private int size;
    //create deque by useing LinkedList with two pointers
    private class node<Item>{
        private node prev;
        private Item content;
        private node next;
        private node(node prev, Item content, node next){
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
        this.sentinel = new node(sentinel, null, sentinel);
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
        node originalFirstNode = sentinel.next;
        node newFirstNode = new node(sentinel, item, originalFirstNode);
        originalFirstNode.prev = newFirstNode;
        sentinel.next = newFirstNode;
        size++;
    }

    //add the item to the end
    public void addLast(Item item){
        validateArguemnt(item);
        node originalLastNode = sentinel.prev;
        node newLastNode = new node(originalLastNode, item, sentinel);
        originalLastNode.next = newLastNode;
        sentinel.prev = newLastNode;
        size++;
    }

    //remove and return the item from the front
    public Item removeFirst(){
        validateSize(this.size);
        node firstNode = sentinel.next;
        node secondFirstNode = sentinel.next.next;
        sentinel.next = secondFirstNode;
        secondFirstNode.prev = sentinel;
        Item content = (Item)firstNode.content;
        firstNode.prev = null;
        firstNode.next = null;
        size--;
        return content;
    }

    //remove and return the item from the end
    public Item removeLast(){
        validateSize(this.size);
        node lastNode = sentinel.prev;
        node sencondLastNode = sentinel.prev.prev;
        sencondLastNode.next = sentinel;
        sentinel.prev = sencondLastNode;
        Item content = (Item)lastNode.content;
        lastNode.prev = null;
        lastNode.next = null;
        return content;
    }

    //return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item>{
        //hasNext();
        private node first = sentinel;
        @Override
        public boolean hasNext(){
            return first.next != first;
        }

        //next()
        @Override
        public Item next(){
            if(!hasNext()){
                throw new java.util.NoSuchElementException("no more items to return");
            }
            first = first.next;
            return (Item)first.content;
            //potential bug
            //boss = boss.next;
            //return (Item)boss.item;
        }

        //remove();
        @Override
        public void remove(){
            throw new java.lang.UnsupportedOperationException("This operation is not supported");
        }

    }

    // unit testing (optional)
    public static void main(String[] args){

    }
}
