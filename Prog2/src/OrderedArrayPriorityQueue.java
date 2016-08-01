import java.util.Iterator;

public class OrderedArrayPriorityQueue<E> implements PriorityQueue<E> {
	
	private OrderedArrayList<E> storage;
	private int maxSize;
	private int currentSize;
	
	public OrderedArrayPriorityQueue(int size) {
		storage = new OrderedArrayList<>(size);
		maxSize = size;
		currentSize = 0;
	}

	@Override
	public boolean insert(E object) {
		if(currentSize < maxSize){
			storage.insert(object);
			currentSize++;
			return true;
		}
		return false;
	}

	@Override
	public E remove() {
		return storage.removeMin();
	}

	@Override
	public E peek() {
		return storage.get(0);
	}

	@Override
	public boolean contains(E obj) {
		return storage.contains(obj);
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public void clear() {
		currentSize = 0;
		storage.clear();
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public boolean isFull() {
		return currentSize == maxSize;
	}

	@Override
	public Iterator<E> iterator() {
		return storage.iterator();
	}

}
