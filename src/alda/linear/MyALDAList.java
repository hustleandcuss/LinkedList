package alda.linear;

/*
 * Olivia Lennerö
 * 911119-5781
 * olivialennero@gmail.com
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyALDAList<E> implements ALDAList<E> {
	private Node<E> first;
	private int theSize = 0;
	
	private static class Node<E> {
		E element;
		Node<E> next;
		
		
		public Node(E element) {
			this.element = element;
			
		}
	}
	
	public MyALDAList() {
		clear();
		
	}
	
	

	@Override
	public Iterator<E> iterator() {
		return new LikedListIterator();
		
	}
	
	private class LikedListIterator implements Iterator<E> {
		
		private Node<E> current = first;
		private Node<E> prev;
		private boolean okRemove = false;
		
		@Override
		public boolean hasNext() {
			if(current == null || current.element == null) {
				return false;
				
			} else {
				return true;
				
			}
		}
		
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
				
			}
			
			E nextElement = current.element;
			prev = current;
			current = current.next;
			okRemove = true;
			return nextElement;
			
		}
	
		public void remove() {
			if(!okRemove) {
				throw new IllegalStateException();
			}
						
			MyALDAList.this.remove(prev.element);
				
			okRemove = false;
					
		}
	}
	
	@Override
	public void add(E element) {
		if(first == null || first.element == null) {
			first = new Node<E>(element);
			theSize++;
			
		} else {
			add(size(), element);
			
		}
	}

	@Override
	public void add(int index, E element) {
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> n = new Node<E>(element);
		
		if(index == 0) {
			n.next = first;
			first = n;
			
		} else if(index > size() -1) {
			for(int i = 0; i < index; i ++) {
				if(i == index - 1) {
					Node<E> before = getNode(i);
					before.next = n;
					
				}
			}	
		} else {
			for(int i = 0; i < index; i++) {
				if(i == index - 1) {
					Node<E> after = getNode(index);
					n.next = after;
					
					Node<E> before = getNode(i);
					before.next = n;
								
						
				
				}
			}
		}
		
		theSize++;
	}

	@Override
	public E remove(int index) {
		Node<E> n = getNode(index);
		
		if(index == 0) {
			first = first.next;
			
		} else {
			Node<E> before = getNode(index - 1);
			before.next = n.next;
			n.next = null;
			
		}
		
		theSize--;
		return n.element;
		
	}

	@Override
	public boolean remove(E element) {
		if(contains(element)) {
			remove(indexOf(element));
			
			return true;
			
		} else {
			return false;
			
		}
	}

	@Override
	public E get(int index) {
		if(index < 0 || index > size() -1 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> n = getNode(index);
		return n.element;
		
	}
	
	public Node<E> getNode(int index) {
		Node<E> n = first;
		
		if(index < 0 || index > size() - 1 || isEmpty()) {
			throw new IndexOutOfBoundsException();
			
		}
	
		for(int i = 0; i < index; i ++) {
			n = n.next;
				
		}
			
		return n;
			
	}

	@Override
	public boolean contains(E element) {
		if(element == null) {
			throw new NullPointerException();
		}
		
		for(int i = 0; i < size(); i++) {
			Node<E> n = getNode(i);
			
			if(n.element == element || n.element.equals(element)) {
				return true;
				
			}
		}
		
		return false;
	}

	@Override
	public int indexOf(E element) {
		if(contains(element)) {
			int j = -1;
		
		for(int i = 0; i < size(); i++) {
			if(element == get(i) || element.equals(get(i))) {
				j = i;
				break;
				
			}
		}
		
		return j;		
		
		} else {
			return -1;
			
		}
	}
		

	@Override
	public void clear() {
		doClear();
		
	}
	
	private void doClear() {
		first = new Node<E>(null);
		first.next = null;
		
		theSize = 0;
		
	}
	
	private boolean isEmpty() {
		return size() == 0;
		
	}

	@Override
	public int size() {
		return theSize;
		
	}
	
	public String toString() {
		if(isEmpty()) {
			return "[]";
			
		} else {
			String str = "[";
			for(int i = 0; i < size(); i++) {
				if(i == size() -1) {
					str += get(i);
					
				} else
				str += get(i) + ", ";
				
			}
			
			str += "]";
			return str;
			
		}
	}
}