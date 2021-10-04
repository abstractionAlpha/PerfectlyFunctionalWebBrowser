package com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.list.positional;

import com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.Position;

public class PositionalLinkedList<E> implements PositionalList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Position<E> first() {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> last() {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> addFirst(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> addLast(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> addBefore(Position<E> p, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> addAfter(Position<E> p, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	public E set(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public E remove(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class Node<E> implements Position<E> {
		
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}

		public E getElement() throws IllegalStateException {
			if (next == null)  // Works with sentinel node
				throw new IllegalStateException("Position is no longer valid");
			
			return element;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setElement(E element) {
			this.element = element;
		}
		
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
	}

}
