//  this one got 95 grades

//import java.lang.Math;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private class Node {
		Item item;
		Node next;
	}
	
	private Node first;
	private int length;
	
	private int getRandomNum() {
		 int random = StdRandom.uniform(length);
//		double random = Math.random() * length;
		return random;
	}
	
	public Iterator<Item> iterator() {
		Iterator<Item> it = new Iterator<Item>() {
			int times = 0;
			boolean[] hasIterated = new boolean[length];		
			
			@Override
			public boolean hasNext() {
				return times < length;
			}
			
			@Override
			public Item next() {
				if (times >= length) {
					throw new NoSuchElementException("no element");
				}
				times++;
				int random = getRandomNum();
				while (hasIterated[random]) {
					random = getRandomNum();
				}
				hasIterated[random] = true;
				int i = 0;
				Node current = first;
				while (i < random) {
					current = current.next;
					i++;
				}
				return  current.item;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove is not supported");
			}
		};
		return it;
	}
	
	public RandomizedQueue() {
		length = 0;
		first = null;
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	public int size() {
		return length;
	}
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("the argument is null");
		}
		length++;
		if (first == null) {
			first = new Node();
			first.item = item;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
		}
	}
	
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}
		length--;
		
		if (length == 0) {
			Item res = first.item;
			first = null;
			return res;
		}
		int randomNum = getRandomNum();
		if (randomNum == 0) {
			Item res = first.item;
			first = first.next;
			return res;
		}
		int preNum = randomNum - 1;
		Node prev = first;
		int i = 0;
		while (i < preNum) {
			i++;
			prev = prev.next;
		}
		Node cur = prev.next;
		if (prev.next != null) {
			prev.next = cur.next;
			cur.next = null;
		} else {
			prev.next = null;
		}
		return cur.item;
	}
	
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}
		int randomNum = getRandomNum();
		Node cur = first;
		int i = 0;
		while (i < randomNum) {
			i++;
			cur = cur.next;
		}
		return cur.item;
	}

	public static void main(String[] args) {
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
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
		
		StdOut.println("------------sample--------------");
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
