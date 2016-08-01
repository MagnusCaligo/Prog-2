import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayList<E> implements OrderedListADT<E>{
	
	private int currentSize;
	private E[] storage;
	private long sequenceNumber;
	private int maxSize;
	
	public OrderedArrayList(int size){
		this.maxSize = size;
		storage = (E[]) new Object[size];
		currentSize = 0;
	}
	

	@Override
	public void insert(E obj) {
		if(this.isFull())
			throw new RuntimeException();
		int location = this.findInsertionPoint(obj, 0, currentSize-1);
		for(int i = this.currentSize; i > location; i--)
			storage[i] = storage[i -1];
		storage[location] = obj;
		this.currentSize++;
	}

	@Override
	public E remove(int index) {
		if(index >= this.currentSize)
			throw new RuntimeException();
		E object = storage[index];
		for(int i = index; i < this.currentSize-1; i++)
			storage[i] = storage[i+1];
		this.currentSize--;
		return object;
	}

	@Override
	public E remove(E obj) {
		int location = this.find(obj);
		if(location == -1)
			return null;
		E objectToRemove = storage[location];
		for(int i = location; i < this.currentSize-1; i++)
			storage[i] = storage[i+1];
		this.currentSize--;
		return objectToRemove;
		
	}
	
	private int binarySearch(E obj, int lo, int hi){
		if(lo > hi){
			if(lo < this.currentSize-1 && ((Comparable<E>)obj).compareTo(storage[lo])==0)
				return lo;
			return -1;
		}
		int mid = (lo+hi)/2;
		if(((Comparable<E>)obj).compareTo(storage[mid])<=0)
			return binarySearch(obj, lo, mid-1);
		return binarySearch(obj, mid+1, hi);
	}

	@Override
	public E removeMin() {
		E obj = storage[0];
		for(int i = 0; i<this.currentSize; i++)
			storage[i] = storage[i+1];
		this.currentSize--;
		return obj;
	}

	@Override
	public E removeMax() {
		E obj = storage[this.currentSize-1];
		this.currentSize--;
		return obj;
	}

	@Override
	public E get(int index) {
		if(index >= this.currentSize || index < 0)
			throw new IndexOutOfBoundsException();
		return storage[index];
	}

	@Override
	public E get(E obj) {
		int location = this.binarySearch(obj, 0, this.currentSize-1);
		if(location>=0)
			return storage[location];
		return null;
	}

	@Override
	public int find(E obj) {
		return this.binarySearch(obj, 0, this.currentSize-1);
	}

	@Override
	public boolean contains(E obj) {
		return this.binarySearch(obj, 0, this.currentSize -1) >= 0;
	}

	@Override
	public void clear() {
		this.currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return !(this.currentSize>0);
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	
	@Override
	public boolean isFull() {
		return this.currentSize == this.maxSize;
	}
	
	private int findInsertionPoint(E obj, int min, int max){
		int mid = (min + max)/2;
		if(min > max)
			return min; 
		if(((Comparable<E>)obj).compareTo(storage[mid])>=0)
			return findInsertionPoint(obj, mid+1, max);
		return findInsertionPoint(obj, min, mid-1);
	}
	
	class IteratorHelper implements Iterator<E>{
		private int iterIndex;
		private long modCheck;
		
		public IteratorHelper(){
			iterIndex = 0;
			modCheck = sequenceNumber;
		}

		@Override
		public boolean hasNext() {
			if(modCheck != sequenceNumber) throw new ConcurrentModificationException();
			return iterIndex < currentSize;
		}

		@Override
		public E next() {
			if(!hasNext()) throw new NoSuchElementException();
			return storage[iterIndex++];
		}
		
	}

	

}
