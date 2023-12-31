package İsletim;

public class ProcessItems { //Proseslerin bilgilerinin tutulduğu sınıf.
	private int id;
	private int suspend;
	private int burstTime;
	private int arrival;
	private int priority;
	private int yazici; 
	private int tarayici; 
	private int modem; 
	private int CD;
	private int Mbayt;
	
	
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuspend() {
        return suspend;
    }

    public void setSuspend(int suspend) {
        this.suspend = suspend;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getYazici() {
        return yazici;
    }

    public void setYazici(int yazici) {
        this.yazici = yazici;
    }

    public int getTarayici() {
        return tarayici;
    }

    public void setTarayici(int tarayici) {
        this.tarayici = tarayici;
    }

    public int getModem() {
        return modem;
    }

    public void setModem(int modem) {
        this.modem = modem;
    }

    public int getCD() {
        return CD;
    }

    public void setCD(int CD) {
        this.CD = CD;
    }

    public int getMbayt() {
        return Mbayt;
    }

    public void setMbayt(int Mbayt) {
        this.Mbayt = Mbayt;
    }
}
