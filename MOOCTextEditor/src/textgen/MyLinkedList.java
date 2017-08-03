package textgen;

//import org.reactfx.util.LL;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	public static void main(String [] args){
		MyLinkedList<Integer> list = new MyLinkedList<>();
		for(int i = 1; i < 4; i++){
			list.add(0, i);
		}
		int k = 1;
	}

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.size = 0;
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException("object cannot be null");
		}
		LLNode<E> newNode = new LLNode<E>(element);
		if(this.size == 0){
			this.tail.prev = newNode;
			this.head.next = newNode;
			newNode.next = this.tail;
			newNode.prev = this.head;
		}else {
			LLNode<E> lastNode = this.tail.prev;
			lastNode.next = newNode;
			this.tail.prev = newNode;
			newNode.next = this.tail;
			newNode.prev = lastNode;
		}
		this.size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("out of bound");
		}
		LLNode<E> cur = this.head.next;
		for(int i = 0; i < index; i++){
			cur = cur.next;
		}
		return cur.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException,
											NullPointerException
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException("Remember object cannot store null pointers");
		}
		if(index > this.size || index < 0){
			throw new IndexOutOfBoundsException("Out of bound");
		}
		LLNode<E> prev = this.head;
		for(int i = 0; i < index ; i++){
			prev = prev.next;
		}
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> nextNode = prev.next;
		nextNode.prev = newNode;
		newNode.next = nextNode;
		prev.next = newNode;
		newNode.prev = prev;

		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("out of bound");
		}
		if(this.size == 0){
			return null;
		}
		LLNode<E> prev = this.head;
		for(int i = 0; i < index; i++){
			prev = prev.next;
		}
		LLNode<E> deletedNode = prev.next;
		prev.next = deletedNode.next;
		deletedNode.next.prev = prev;
		deletedNode.prev = null;
		deletedNode.next = null;
		this.size--;
		return deletedNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException,
											NullPointerException
	{
		// TODO: Implement this method
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("Out of bound");
		}
		if(element == null){
			throw new NullPointerException("null pointer");
		}
		LLNode<E> prev = this.head;
		for(int i = 0; i < index; i++){
			prev = prev.next;
		}
		LLNode<E> node = prev.next;
		E oldValue = node.data;
		node.data = element;
		return oldValue;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode()
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}

}
