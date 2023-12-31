package İsletim;

public class MemoryAllocateAlgorithms {
	Mab head;
	 
	 public void addToEnd(int size) { //Sona ekleme 
	        Mab newNode = new Mab(size);

	        if (head == null) {
	            head = newNode;
	        } else {
	            Mab temp = head;
	            while (temp.next != null) {
	                temp = temp.next;
	            }
	            temp.next = newNode;
	            newNode.prev = temp;
	        }
	    }
	 
	 public Mab findFirstFit(int requestedSize) { //FirstFit Algoritmasına göre bellekte yer kontrolü
	        Mab currentNode = head;

	        while (currentNode != null) {
	            if (!currentNode.allocated && currentNode.size >= requestedSize) {
	                return currentNode;
	            }

	            currentNode = currentNode.next;
	        }

	        return null; // Uygun blok bulunamadı
	    }
	 
	 
	 public Mab findBestFit(int requestedSize) { //Bestfit Algoritmasına göre bellekte yer kontrolü
	        Mab bestFit = null;
	        Mab currentNode = head;

	        while (currentNode != null) {
	            if (!currentNode.allocated && currentNode.size >= requestedSize) {
	                if (bestFit == null || currentNode.size < bestFit.size) {
	                    bestFit = currentNode;
	                }
	            }

	            currentNode = currentNode.next;
	        }

	        return bestFit; // Uygun blok bulunamazsa null döndürülür
	    }
	 
	 public Mab findWorstFit(int requestedSize) { //WorstFit Algoritmasına göre bellekte yer kontrolü
	        Mab worstFit = null;
	        Mab currentNode = head;

	        while (currentNode != null) {
	            if (!currentNode.allocated && currentNode.size >= requestedSize) {
	                if (worstFit == null || currentNode.size > worstFit.size) {
	                    worstFit = currentNode;
	                }
	            }

	            currentNode = currentNode.next;
	        }

	        return worstFit; // Uygun blok bulunamazsa null döndürülür
	    }

	 public Mab findNextFit(int requestedSize, Mab lastAllocatedBlock) {
	        Mab currentNode;

	        // Eğer daha önce hiç yerleştirme yapılmamışsa, başlangıç düğümünden başla
	        if (lastAllocatedBlock == null) {
	            currentNode = head;
	        } else {
	            currentNode = lastAllocatedBlock.next;
	        }

	        while (currentNode != null) {
	            if (!currentNode.allocated && currentNode.size >= requestedSize) {
	                return currentNode;
	            }

	            currentNode = currentNode.next;
	        }

	        // Eğer sona ulaşıldıysa baştan devam et
	        currentNode = head;
	        while (currentNode != lastAllocatedBlock) {
	            if (!currentNode.allocated && currentNode.size >= requestedSize) {
	                return currentNode;
	            }

	            currentNode = currentNode.next;
	        }

	        return null; // Uygun blok bulunamadı
	    }
	 
	 public void display() { 
	        Mab temp = head;
	        while (temp != null) {
	            System.out.print("[Size: " + temp.size + ", Allocated: " + temp.allocated + "] ");
	            temp = temp.next;
	        }
	        System.out.println();
	    }
}
