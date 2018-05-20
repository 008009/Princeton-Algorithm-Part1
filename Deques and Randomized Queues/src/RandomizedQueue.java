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
        return (4 * size) < (array.length) ;
    }

    //construct an empty randomized queue
    public RandomizedQueue(){
        this.size = 0;
        Item[] a = (Item[]) new Object[1];
        this.array = a;
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
        if(this.size < array.length){
            size++;
            array[size - 1] = item;
        }else{
            resize(size * 2);
            size++;
            array[size - 1] = item;
        }
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
        int index = StdRandom.uniform(0, array.length);
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
            return example[startIndex++];
            // first return example[0], then startIndex++;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("This operation is not supported");
        }
    }

    //unit testing (optional)
    public static void main(String[] args){
        RandomizedQueue<Integer> number = new RandomizedQueue<Integer>();
        number.enqueue(10);
//        System.out.println(number.size);
//        System.out.println(number.array.length);
        number.enqueue(20);
        number.enqueue(19);  // 10 20 19
        number.enqueue(39);
        number.enqueue(99);
        number.dequeue();
        number.dequeue();
        number.dequeue();
        number.dequeue();
        Iterator<Integer> it = number.iterator();
        for(int i = 0; i < number.size; i++){
            System.out.println(it.next());
        }
    }
}
