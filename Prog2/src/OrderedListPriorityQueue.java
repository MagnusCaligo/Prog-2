import java.util.Arrays;
import java.util.Iterator;

public class OrderedListPriorityQueue<E> implements PriorityQueue<E>{

	private Node<E> head;
	private Node<E> tail;
	private int currentSize;
	
	public OrderedListPriorityQueue(){
		currentSize = 0;
	}
	
	public String toString(){
		String[] array = new String[currentSize];
		Node current = head;
		int i = 0;
		while(current != null){
			array[i] = current.data.toString();
			i++;
			current = current.next;
		}
		return Arrays.toString(array);
		
	}
	
	public E remove(E obj){
		
		Node<E> previous = null;
		Node<E> current = head;
		
		while(current != null && ((Comparable<E>)obj).compareTo(current.data) < 0){
			previous = current;
			current = current.next;
		}
		if(current == null)
			return null;
		if(previous == null){
			head = tail = null;
			return current.data;
		}
		previous.next = current.next;
		return current.data;
		
	}
	
	@Override
	public boolean insert(E object) {
		if(head == null){
			head = tail = new Node<E>(object);
			currentSize++;
			return true;
		}
		Node<E> previous = null;
		Node<E> current = head;
		E data = object;
		while(current != null && ((Comparable<E>)object).compareTo(current.data) <= 0){
			previous = current;
			current = current.next;
		}
		if(current == null){
			Node<E> newNode = new Node<E>(object);
			previous.next  = newNode;
			head = tail = newNode;
			currentSize++;
			return true;
		}
		Node<E> newNode = new Node<E>(object);
		if(previous == null){
			newNode.next = head;
			head = newNode;
			currentSize++;
			return true;
		}
		newNode.next = current;
		previous.next = newNode;
		currentSize++;
		return true;
	}

	@Override
	public E remove() {
		if(head == null)
			return null;
		E obj = head.data;
		if(head.next != null)
			head = head.next;
		currentSize--;
		return obj;
	}

	@Override
	public E peek() {
		if(head != null)
			return head.data;
		return null;
	}

	@Override
	public boolean contains(E obj) {
		Node<E> cur = head;
		while(cur != null && ((Comparable<E>)obj).compareTo(cur.data)!= 0){
			cur = cur.next;
		}
		if(cur == null)
			return false;
		return true;
		
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public void clear() {
		currentSize = 0;
		head = null;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	
	class IteratorHelper implements Iterator{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class Node<E>{
		
		public Node next;
		public E data;
		
		public Node(E data){
			this.data = data;
		}
		
	}

}
