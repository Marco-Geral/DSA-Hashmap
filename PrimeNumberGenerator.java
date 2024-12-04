public class PrimeNumberGenerator {
    PrimeNode head;

    @Override
    public String toString() {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator() {
        head = new PrimeNode(2);
    }

    public int currentPrime() {
        return head.value;
    }

    // Helper method to check if a number is prime
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int nextPrime() {
        if (head.next == null) {
            sieveOfEratosthenes();
        }
        
        head = head.next;

        return head.value;
    }

    public void sieveOfEratosthenes() {

        Boolean notPrime[] = new Boolean[head.value*2+1];
    	
    	for(int i = 0; i < notPrime.length; i++) {
    		notPrime[i] = false;
    	}
    	
    	int jump = 2;
    	
    	while(jump < notPrime.length) {
    		int counter = jump + jump;
    		
    		while(counter < notPrime.length) {
    			notPrime[counter] = true;
    			counter += jump;
    		}
    		
    		jump++;
    	}
    	
    	for(int i = 0; i < notPrime.length; i++) {
			if(notPrime[i] == false && i != 0 && i != 1) {
				if(!contains(head, i) && i > head.value) {
					head = insert(head, i);
				}
			}
		}

    }

    public static boolean contains(PrimeNode head, int i) {
        PrimeNode current = head;
        while (current != null) {
            if (current.value == i) {
                return true; // Value found, return true
            }
            current = current.next; // Move to the next node
        }
        return false; // Value not found, return false
    }

    public static PrimeNode insert(PrimeNode head, int i) {
        // Create a new node with the value i
        PrimeNode newNode = new PrimeNode(i);
        PrimeNode ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = newNode;
        newNode.next = null;

        return head; // Return the updated head
    }



}
