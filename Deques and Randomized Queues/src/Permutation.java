import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){
            String content = StdIn.readString();
            rq.enqueue(content);
        }
        Iterator<String> it = rq.iterator();
        for(int i = 0; i < k; i++){
            System.out.println(it.next());
        }
    }
}

