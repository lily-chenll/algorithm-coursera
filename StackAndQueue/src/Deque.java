import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
	
	private Node first;
	private Node last;
	private int len;
	
	public Deque() {
		first = null;
		last = null;
		len = 0;
	}
	
	@Override
	public Iterator<Item> iterator() {
		Iterator<Item> it = new Iterator<Item>() {
			private Node current = first;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}
			
			@Override
			public Item next() {
				if (current == null) {
					throw new NoSuchElementException("no element");
				}
				Item item = current.item;
				current = current.next;
				return item;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove is not supported");
			}
		};
		return it;
	}
	
	public boolean isEmpty() {
		return len == 0;
	}
	
	
	public int size() {
		return len;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null");
		}
		len++;
		if (first == null) {
			first = new Node();
			first.item = item;
			last = first;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
			oldFirst.prev = first;
		}
		
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null");
		}
		len++;
		if (last == null) {
			last = new Node();
			last.item = item;
			first = last;
		} else {
			Node oldLast = last;
			last = new Node();
			last.item = item;
			oldLast.next = last;
			last.prev = oldLast;
		}
	}
	
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}
		len--;
		Item res = first.item;
		if (first.next != null) {
			first = first.next;
			first.prev = null;
		} else {
			first = null;
			last = null;
		}
		return res;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("The deque is empty.");
		}
		len--;
		Item item = last.item;
		if (last.prev != null) {
			last = last.prev;
			last.next = null;
		} else {
			first = null;
			last = null;
		}		
		return item;
	}

	public static void main(String[] args) {
		Deque<String> d = new Deque<String>();

		d.addFirst("test1");
		d.addFirst("test2");
		d.addLast("test3");
		
		StdOut.println(d.removeLast());
		StdOut.println(d.removeLast());
		StdOut.println("size: " + d.size());
		
		d.addLast("test4");
		d.addFirst("test5");
		
		Iterator<String> iterator = d.iterator();
		while (iterator.hasNext()) {
			StdOut.println("iterator: " + iterator.next());
		}

		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.size());
		
		iterator.remove();
	}

}
