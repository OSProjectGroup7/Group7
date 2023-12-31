package İsletim;

public class MemoryAllocator { //memory düzenleyici
	 MemoryAllocateAlgorithms memoryList;
	    int realTimeMemorySize; // Realtime processler için ayrılan bellek boyutu
	    Mab lastAllocatedBlock; // Next fit algoritması için son yerleştirilen blok

	    public MemoryAllocator(int totalSize, int realTimeMemorySize) {
	        memoryList = new MemoryAllocateAlgorithms();
	        this.realTimeMemorySize = realTimeMemorySize;

	        // Realtime belleği ilk düğüm olarak ekleyelim
	        memoryList.addToEnd(realTimeMemorySize);

	        // Geri kalan kısmı normal bellek olarak ekleyelim
	        memoryList.addToEnd(totalSize - realTimeMemorySize);
	    }

	    public void allocateMemory(ProcessItems process, String algorithm) { 
	        int requestedSize = process.getMbayt();
	        
	        Mab allocatedBlock = null;

	        switch (algorithm.toLowerCase()) { //Algoritma seçme kısmı
	            case "firstfit":
	                allocatedBlock = memoryList.findFirstFit(requestedSize);
	                break;
	            case "bestfit":
	                allocatedBlock = memoryList.findBestFit(requestedSize);
	                break;
	            case "worstfit":
	                allocatedBlock = memoryList.findWorstFit(requestedSize);
	                break;
	            case "nextfit":
	                allocatedBlock = memoryList.findNextFit(requestedSize, lastAllocatedBlock);
	                break;
	            default:
	                System.out.println("Invalid algorithm.");
	                return;
	        }

	        if (allocatedBlock != null) {
	            // Bellekte yer bulundu, prosesi yerleştirelim
	            allocatedBlock.allocated = true;
	            lastAllocatedBlock = allocatedBlock; // Son yerleştirilen bloğu güncelle
	            //System.out.println(process.id + " allocated successfully with " + algorithm + " algorithm.");
	            deallocateMemory();
	        } else {
	            // Bellekte yer bulunamadı
	            System.out.println(process.getId() + " allocation failed with " + algorithm + " algorithm. Not enough space available.");
	            
	        }
	        
	    }

	    public void deallocateMemory() { //temizleme kısmı.
	        Mab currentNode = memoryList.head;

	        while (currentNode != null) {
	            if (currentNode.allocated) {
	                currentNode.allocated = false;
	                //System.out.println("Memory deallocated successfully.");
	                return;
	            }

	            currentNode = currentNode.next;
	        }

	        System.out.println("No allocated memory to deallocate.");
	    }

	    public void displayMemoryStatus() {
	        memoryList.display();
	    }
}
