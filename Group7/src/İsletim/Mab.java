package İsletim;

public class Mab {
	MemoryAllocateAlgorithms mablist = new MemoryAllocateAlgorithms();
    int size;
    boolean allocated;
    Mab next;
    Mab prev;

    public Mab(int size) { //bellek blokları.
        this.size = size;
        this.allocated = false;
        this.next = null;
        this.prev = null;
    }
}
