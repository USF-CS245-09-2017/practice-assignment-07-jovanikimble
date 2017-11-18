
/**
 * Implementation of a Hash Table using separate
 * chaining
 * 
 * @author jovanikimble
 *
 */
public class Hashtable {
	
	Node[] hashtable;
	int size;
	int count;
	
	public Hashtable() {
		this.hashtable = new Node[100000];
		this.size = hashtable.length;
		this.count = 0;
	}
	

	/**
	 * Returns key associated with passed in value
	 * or null if key does not exist
	 * 
	 * @param key
	 * @return value associated with key
	 */
	public String get(String key) {
		if(this.hashtable[getPos(key)] != null) {
			Node temp = this.hashtable[getPos(key)];
			while(temp != null) {
				if(temp.key.equals(key)) {
					return temp.value;
				}
				temp = temp.next;
			}
		}
		return null;
	}

	/**
	 * Puts a key value pair in the hash table;
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		Node node = new Node(key, value);
		boolean found = false;
		
		Node temp = this.hashtable[getPos(key)];
		while(temp != null) {
			if(temp.key.equals(key)) {
				temp.value = value;
				found = true;
			}
			temp = temp.next;
		}
		
		if(found == false) {
			node.next = this.hashtable[getPos(key)];
			this.hashtable[getPos(key)] = node;
		}
	}

	/**
	 * Returns whether or not key is in 
	 * Hash Table
	 * 
	 * @param key
	 * @return boolean 
	 */
	public boolean contains(String key) {
		return containsKey(key);
	}

	/**
	 * Removes key value pair, returns value
	 * associated with key value pair being removed
	 * 
	 * @param key
	 * @return value
	 */
	public String remove(String key) {
		String value =  null;
		Node node = hashtable[getPos(key)];
		
		if(node == null) {
			throw new NullPointerException("Key not in table");
		}
		
		Node prev = null;
		while(node != null) {
			if(node.key.equals(key)) {
				value = node.value;
				break;
			}
			prev = node;
			node = node.next;
		}
		
		if(prev != null) {
			prev.next = node.next;
		} else {
			hashtable[getPos(key)] = node.next;
		}
		return value;
	}

	/**
	 * See contains
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		if(this.hashtable[getPos(key)] != null) {
			Node node = this.hashtable[getPos(key)];
			
			while(node != null) {
				if(node.key.equals(key)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}
	
	/**
	 * Returns array pos where a key can be
	 * found 
	 * 
	 * @param key
	 * @return int
	 */
	private int getPos(String key) {
		return Math.abs(key.hashCode()) % this.size;
	}
	
	/**
	 * Private Node class 
	 * 
	 * @author jovanikimble
	 *
	 */
	private class Node {
		String key;
		String value;
		Node next;
		
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
}
