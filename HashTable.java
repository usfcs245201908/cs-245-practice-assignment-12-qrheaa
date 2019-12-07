import java.util.*;

public class Hashtable {

	HashNode[] bucket;
	double LOAD_THRESHOLD = (float)0.5;
	int entries;
	int size;


	public Hashtable() {
		size = 100;
		LOAD_THRESHOLD = 0.5;
		bucket = new HashNode[size];
		entries = 0;

	}

	public int hashCode(String key) {
		return Math.abs(key.hashCode()% size);
	}

	//returns true if key matches argument
	public boolean containsKey(String key) {
		return(bucket[hashCode(key)]!=null);
	}

	//this method will retrieve the key of the designated value
	public String get(String key){
		HashNode head = bucket[hashCode(key)];

		while(head != null)	{
			if (head.key == key){
				return head.value;
			} 
			else 
			{
				head = head.next;
			}
		}
		return null;
	
	}

	//this method will add in values based on key 
	public void put(String key, String value){
		HashNode head = bucket[hashCode(key)];
		
		if (head == null)	{
			bucket[hashCode(key)] = new HashNode(key, value);
		} 
		else 
		{
			while (head != null) {
				if (head.key.equals(key)) {
					head.value = value;
					return;
				}	
			head = head.next;
		}
		
		HashNode node = new HashNode(key, value);

		node.next = bucket[hashCode(key)];

		bucket[hashCode(key)] = node;
		}

		++entries;

		if ((entries * 1.0) / size >= LOAD_THRESHOLD){
				HashNode[] temp = bucket;
				size *= 2;
				bucket = new HashNode[size];
		}
	}

	//This method will remove the said key and bring the most relevant piece forward 
	public String remove(String key) throws Exception {
		HashNode head = bucket[hashCode(key)];

		if (head != null) {
			if (head.key.equals(key)) {
				bucket[hashCode(key)] = head.next;
			entries--;
			return head.value;
			} 
		}
		else { //separate chaining- linked list
			HashNode prev = head;
			HashNode curr = null;

			while(prev.next != null) {
				curr = prev.next;
				if(curr.key.equals(key)) {
					prev.next = curr.next; //deleting here
					entries--;
				}
			}
			return curr.value;
		}
		return null;
	}


}
