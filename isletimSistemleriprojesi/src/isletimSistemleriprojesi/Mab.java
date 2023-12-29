package isletimSistemleriprojesi;

import java.util.LinkedList;

public class Mab {
	private int offset;
    private int size;
    private boolean allocated;
    private Mab next;
    private Mab prev;
    
    public enum MemAllocAlg {
        FIRST_FIT, NEXT_FIT, BEST_FIT, WORST_FIT
    }
    
    //private LinkedList<Memory> Memorylist ;
    
    
    
    public Mab(int offset, int size, boolean allocated) {
        this.offset = offset;
        this.size = size;
        this.allocated = allocated;
        this.next = null;
        this.prev = null;
    }
    
    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public boolean isAllocated() { return allocated; }
    public void setAllocated(boolean allocated) { this.allocated = allocated; }

    public Mab getNext() { return next; }
    public void setNext(Mab next) { this.next = next; }

    public Mab getPrev() { return prev; }
    public void setPrev(Mab prev) { this.prev = prev; }
    
    
    
    public static Mab memChk(Mab arena, int size) {      // uygun bir blok arar
        Mab current = arena;
        while (current != null) {
            if (!current.isAllocated() && current.getSize() >= size) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public static Mab memAlloc(Mab arena, int size) {       //bellek tahsis edere
        Mab blockToAllocate = memChk(arena, size);
        if (blockToAllocate != null) {
            return memSplit(blockToAllocate, size);
        }
        return null;
    }

    public static Mab memFree(Mab mab) {           ///    belleği serbest bırakır
        if (mab != null) {
            mab.setAllocated(false);
            if (mab.getNext() != null && !mab.getNext().isAllocated()) {
                mab = memMerge(mab);
            }
            if (mab.getPrev() != null && !mab.getPrev().isAllocated()) {
                mab = memMerge(mab.getPrev());
            }
        }
        return mab;
    }
 
    public static Mab memMerge(Mab mab) {              /// bitişik boş blokları birleştirme işlkemi
        if (mab != null && mab.getNext() != null && !mab.getNext().isAllocated()) {
            Mab next = mab.getNext();
            mab.setSize(mab.getSize() + next.getSize());
            mab.setNext(next.getNext());
            if (next.getNext() != null) {
                next.getNext().setPrev(mab);
            }
        }
        return mab;
    }
 
    public static Mab memSplit(Mab mab, int size) {     ////blokları ikiye bölme
        if (mab != null && mab.getSize() > size) {
            Mab newMab = new Mab(mab.getOffset() + size, mab.getSize() - size, false);
            newMab.setNext(mab.getNext());
            newMab.setPrev(mab);
            mab.setNext(newMab);
            mab.setSize(size);
            if (newMab.getNext() != null) {
                newMab.getNext().setPrev(newMab);
            }
            return mab;
        }
        return null;
    }
    
    
}

