package lists;

public class Queue<T> {

    private Node<T> front;
    private Node<T> rear;
    private int size;

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public T dequeue() throws Error {
        if (isEmpty()) {
            throw new Error("The queue is empty");
        }

        T element = front.getContent();
        front = front.getNext();
        size--;

        if (isEmpty()) {
            rear = null;
        }

        return element;
    }

    public T peek() throws Error {
        if (isEmpty()) {
            throw new Error("The queue is empty");
        }

        return front.getContent();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public Node<T> getFront() {
        return front;
    }
}