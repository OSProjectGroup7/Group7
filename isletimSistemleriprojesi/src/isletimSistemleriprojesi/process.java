package isletimSistemleriprojesi;

public class process {

	private int processId;
	private int varisZamani;
	private int oncelik;
	private int burstTime;
	private int Mbayt;
	private int yazici; 
	private int tarayici; 
	private int modem; 
	private int CD;
	private int kesmeZamani;
	
	public process(int id, int vz, int onclk, int pz, int MB, int yzc, int tryc, int mdm, int cd, int kz) {
		
		this.processId = id;
		this.varisZamani = vz;
		this.oncelik = onclk;
		this.burstTime = pz;
		this.Mbayt = MB;
		this.yazici = yzc;
		this.tarayici = tryc;
		this.modem = mdm;
		this.CD = cd;
		this.kesmeZamani = kz;
	}

	
	
	public int processIdGetter()
	{
		return this.processId;
	}
	public int varisZamaniGetter()
	{
		return this.varisZamani;
	}
	public int oncelikGetter()
	{
		return this.oncelik;
	}
	public void oncelikSetter(int i)
	{
		this.oncelik = i;
	}
	public int burstTimeGetter()
	{
		return this.burstTime;
	}
	public void burstTimeSetter(int i)
	{
		this.burstTime = i;
	}
	public int MbaytGetter()
	{
		return this.Mbayt;
	}
	public int yaziciGetter()
	{
		return this.yazici;
	}
	public int tarayiciGetter()
	{
		return this.tarayici;
	}
	public int modemGetter()
	{
		return this.modem;
	}
	public int CDGetter()
	{
		return this.CD;
	}
	public int kesmeZamaniGetter()
	{
		return kesmeZamani;
	}
	public void kesmeZamaniSetter(int i)
	{
		this.kesmeZamani = i;
	}
}
