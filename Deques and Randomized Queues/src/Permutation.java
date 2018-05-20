import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] allString = StdIn.readAllStrings();
        Queue<String> q = new Queue<String>();
        for(int i = 0; i < k; i++){
            int index = StdRandom.uniform(0,allString.length);
            q.enqueue(allString[index]);
        }
        for(int i = 0; i < q.size(); i++){
            System.out.println(q.iterator().next());
        }
    }
}

