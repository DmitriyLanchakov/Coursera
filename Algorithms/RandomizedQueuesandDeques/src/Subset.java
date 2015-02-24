import java.io.StringReader;

/**
 * Created by den on 10.02.15.
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException();

        int subsetSize = Integer.parseInt(args[0]);
        if (subsetSize < 0)
            throw new IllegalArgumentException();
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String line = null;
        while (!StdIn.isEmpty()) {
            if (!StdIn.hasNextLine()) break;
            line = StdIn.readString();
            rq.enqueue(line);
        }

        for (int i = 0; i < subsetSize; i++) {
            System.out.println(rq.dequeue());
        }
    }

}
