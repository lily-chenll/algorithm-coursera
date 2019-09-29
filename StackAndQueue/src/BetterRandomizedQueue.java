//import java.lang.Math;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class BetterRandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Item[] array;
	
	private int getRandomNum(int size) {
		 int random = StdRandom.uniform(size);
//		int random = (int) (Math.random() * size);
		return random;
	}
	
	public BetterRandomizedQueue() {
		size = 0;
		array = (Item[]) new Object[2];
	}
	
	@Override
	public Iterator<Item> iterator() {
		Item[] tempArray = (Item[]) new Object[size];
		for (int i = 0; i < size; i++) {
			tempArray[i] = array[i];
		}
		
		Iterator<Item> it = new Iterator<Item>() {
			private int currentSize = size;
			
			@Override
			public boolean hasNext() {
				return currentSize > 0;
			}
			
			@Override
			public Item next() {
				if (!hasNext()) {
					throw new NoSuchElementException("no element");
				}
				int randomNum = getRandomNum(currentSize);
				Item res = tempArray[randomNum];
				tempArray[randomNum] = tempArray[--currentSize];
				tempArray[currentSize] = null;
				return res;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove is not supported");
			}
		};
		return it;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	private void resize(int newSize) {
		Item[] res = (Item[])new Object[newSize];
		for (int i = 0; i < size; i++) {
			res[i] = array[i];
		}
		array = res;
	}
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("the argument is null");
		}
		if (size == array.length) {
			resize(size * 2);
		}
		array[size++] = item;
	}
	
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}
		if (size * 4 == array.length) {
			resize(size * 2);
		}
		int randomNum = getRandomNum(size);
		Item  res = array[randomNum];
		array[randomNum] = array[--size];
		array[size] = null;
		return res;
	}
	
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}
		int randomNum = getRandomNum(size);
		return array[randomNum];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BetterRandomizedQueue<String> randomQueue = new BetterRandomizedQueue<String>();
		for (int i = 0; i < 6; i++) {
			int j = 6 - i;
			randomQueue.enqueue("test" + j);
		}
		
		StdOut.println("------------Size--------------");
		StdOut.println(randomQueue.size());
		StdOut.println();
		
		StdOut.println("------------Elements 1--------------");
		Iterator<String> it = randomQueue.iterator();
		while (it.hasNext()) {
			StdOut.println(it.next());
		}
		StdOut.println();
		
		StdOut.println("------------Elements 2--------------");
		it = randomQueue.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + ", ");
		}
		StdOut.println();
		
		StdOut.println("------------sample1--------------");
		for (int i = 0; i < 8; i++) {
			StdOut.println(randomQueue.sample());
		}
		StdOut.println();
		
		StdOut.println("------------sample2--------------");
		for (int i = 0; i < 8; i++) {
			StdOut.println(randomQueue.sample());
		}
		StdOut.println();
		
		StdOut.println("------------Size--------------");
		StdOut.println(randomQueue.size());
		StdOut.println();
		
		StdOut.println("------------Empty--------------");
		StdOut.println(randomQueue.isEmpty());
		StdOut.println();
		
		StdOut.println("------------Dequeue--------------");
		for (int i = 0; i < 4; i++) {
			StdOut.println(randomQueue.dequeue());
		}
		StdOut.println();
		
		StdOut.println("------------Size--------------");
		StdOut.println(randomQueue.size());
		StdOut.println();
		
		StdOut.println("------------Dequeue--------------");
		for (int i = 0; i < 2; i++) {
			StdOut.println(randomQueue.dequeue());
		}
		StdOut.println();
		
		StdOut.println("------------Empty--------------");
		StdOut.println(randomQueue.isEmpty());
		StdOut.println();
		
		randomQueue.enqueue(null);
	}

}
