package com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.list.positional;

import com.abstractionalpha.web.browser.perfectly_functional_web_browser.util.Position;

public class PositionalLinkedList<E> implements PositionalList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	/**
	 * Constructor for PositionalLinkedList.
	 * Creates a new PLL with a head and tail sentinel.
	 */
	public PositionalLinkedList() {
		head = new Node<E>(null, null, null);
		tail = new Node<E>(null, head, null);
		head.setNext(tail);
		size = 0;
	}
	
	/**
	 * This method checks if an input position, p, is a valid node.
	 * If it is, it will be casted to a node and returned.
	 * 
	 * @param p - the input position for some method
	 * @return p as a Node
	 * @throws IllegalArgumentException - p isn't a Node or isn't in list
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Invalid position");
		
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null)
			throw new IllegalArgumentException("Input position no longer valid");
		
		return node;
	}
	
	/**
	 * This method casts a node to a position.
	 * If the head or tail is passed in, null will be returned.
	 * This is to encapsulate the sentinel nodes.
	 * 
	 * @param node - the node we want to return
	 * @return node as a position, or null if node is head/tail
	 */
	private Position<E> position(Node<E> node) {
		if (node == head || node == tail)
			return null;
		
		return node;
	}

	/**
	 * Returns the number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns whether or not the list is empty.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the element at the front of the list.
	 * This is wrapped with position() so that if there is nothing in the list,
	 * null will be returned.
	 */
	public Position<E> first() {
		return position(head.getNext());
	}

	/**
	 * Returns the element at the back of the list.
	 * This is wrapped with position() so that if there is nothing in the list,
	 * null will be returned.
	 * For any following methods, we will not clarify this wrapper.
	 */
	public Position<E> last() {
		return position(tail.getPrev());
	}

	/**
	 * Returns the element before p.
	 * We validate p to safely cast to Node.
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	/**
	 * Returns the element after p.
	 * We validate p to safely cast to Node.
	 * For any following methods, we will not clarify this call.
	 */
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	/**
	 * This private helper method adds a node between two other nodes.
	 * 
	 * @param e - the element we are adding to our list
	 * @param before - the node we are adding e after
	 * @param after - the node we are adding e before
	 * @return e as a position
	 */
	private Position<E> addBetween(E e, Node<E> before, Node<E> after) {
		Node<E> node = new Node<E>(e, before, after);
		before.setNext(node);
		after.setPrev(node);
		size++;
		return position(node);
	}

	/**
	 * Adds an element between the head and the thing after the head.
	 */
	public Position<E> addFirst(E e) {
		return addBetween(e, head, head.getNext());
	}

	/**
	 * Adds an element between the tail and the thing before the tail.
	 */
	public Position<E> addLast(E e) {
		return addBetween(e, tail.getPrev(), tail);
	}

	/**
	 * Adds an element before whatever p is inputed.
	 */
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	/**
	 * Adds an element after whatever p is inputed.
	 */
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	/**
	 * This method sets the position p's element to e,
	 * and then returns its old element.
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E toReturn = node.getElement();
		node.setElement(e);
		return toReturn;
	}

	/**
	 * Removes the position p from the PLL and returns its element.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> before = node.getPrev();
		Node<E> after = node.getNext();
		
		before.setNext(after);
		after.setPrev(before);
		size--;
		
		E toReturn = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		
		return toReturn;
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
