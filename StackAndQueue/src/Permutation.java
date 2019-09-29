import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.valueOf(args[0]);
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()) {
			String string = StdIn.readString();
			randomQueue.enqueue(string);
		}
		
		int i = 0;
		while (i < k) {
			StdOut.println(randomQueue.dequeue());
			i++;
		}
	}

}
