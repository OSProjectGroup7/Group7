package İsletim;

public class Rsrc {
	 private int printers;
     private int scanners;
     private int modems;
     private int cds;

     // Constructor
     public Rsrc(int printers, int scanners, int modems, int cds) {
         this.printers = printers;
         this.scanners = scanners;
         this.modems = modems;
         this.cds = cds;
     }

     // Getters and Setters
     public int getPrinters() {
         return printers;
     }

     public void setPrinters(int printers) {
         this.printers = printers;
     }

     public int getScanners() {
         return scanners;
     }

     public void setScanners(int scanners) {
         this.scanners = scanners;
     }

     public int getModems() {
         return modems;
     }

     public void setModems(int modems) {
         this.modems = modems;
     }

     public int getCds() {
         return cds;
     }

     public void setCds(int cds) {
         this.cds = cds;
     }

    
}
