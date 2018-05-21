/*
1. resize the array when there is no space for enqueue
2. double the array when expanding
3. resize the array number of number of index/ array length < 25%
4. choose randomly the element to be removed, replace that with the last element in the array.
 */
import java.lang.Iterable;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] array;
    //helper function
    private void resize(int targetCapacity){
        Item[] afterResize = (Item[]) new Object[targetCapacity];
        System.arraycopy(this.array,0 ,afterResize,0,size);
        array = afterResize;
    }
    private boolean needShrink(){
        return size > 0 && (4 * size) < (array.length) ;
    }

    //construct an empty randomized queue
    public RandomizedQueue(){
        this.size = 0;
        array = (Item[]) new Object[1];
    }

    //is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    //return the number of items on the randomized queue
    public int size(){
        return size;
    }

    //add the item
    public void enqueue(Item item){
        if(item == null){
            throw new java.lang.IllegalArgumentException("Invalid argument");
        }
//        if(size == 0) {
//            RandomizedQueue<Item> newQueue = new RandomizedQueue<>();
//
//        }
//        if(this.size < array.length){
//            size++;
//            array[size - 1] = item;
//        }else{
//            //potential bug
//            resize(array.length * 2);
//            size++;
//            array[size - 1] = item;
//        }
        if(this.size == array.length){
            resize(array.length * 2);
        }
//        size++;
//        array[size - 1] = item;
        array[size] = item;
        size++;
    }

    //remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException("No element to be dequeue");
        }
        int index = StdRandom.uniform(0, size);
        Item result = array[index];
        array[index] = array[size - 1];
        array[size - 1] = null;
        size--;
        if(needShrink()){
            resize(array.length/2);
        }
        return result;
    }

    //return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException("No element to be dequeue");
        }
        int index = StdRandom.uniform(0, size);
        Item Result = array[index];
        return Result;
    }

    //return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        Item[] example;
        int startIndex = 0;
        private ArrayIterator(){
           example = (Item[]) new Object[size];
           System.arraycopy(array,0,example,0,size);
           StdRandom.shuffle(example);
        }
        @Override
        public boolean hasNext(){
            return startIndex <= size - 1;
        }

        @Override
        public Item next(){
            if(!hasNext()){
                throw new java.util.NoSuchElementException("no more items to return");
            }
            Item result = example[startIndex];
            startIndex++;
            return result;
            // first return example[0], then startIndex++; == return exapmle[startIndex++];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("This operation is not supported");
        }
    }

    //unit testing (optional)
    public static void main(String[] args){
        RandomizedQueue<Integer> number = new RandomizedQueue<>();
        number.enqueue(527);
        number.dequeue();
        System.out.println(number.isEmpty());
        number.enqueue(578);
//        number.dequeue();
//        number.dequeue();
//        number.dequeue();
//        number.enqueue(1000);
//      System.out.println(number.sample());
        Iterator<Integer> it = number.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
