package İsletim;

import java.util.LinkedList;
import java.util.Random;


public class RoundRobinList { //Önceliği 3 olanlar için round robin algoritmasını çalıştırır.

	private LinkedList<ProcessItems> queue ;
	private int sayac = 0;
	
	public RoundRobinList() {
		this.queue = new LinkedList<ProcessItems>();
	}
	public LinkedList<ProcessItems> getRoundRobinList(){
		return this.queue;
	}
	
	void RR_add(ProcessItems item) {
		this.queue.addLast(item);

	}
	
	boolean RR_isEmpty() {
		return this.queue.isEmpty();
	}
	
	int RR_execute(int zaman) {

		Random rng = new Random();

		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		Dispatcher dl = ProcessReader.dl;

		String text = "";

		int timer = 0; 
		ProcessItems item = this.queue.get(sayac);

		text = String.format(
				"\033[38;2;%d;%d;%dm proses basladı         (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
				r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

		System.out.println(text);

		timer++;
		item.setBurstTime(item.getBurstTime()-1);

		item.setSuspend(zaman + timer);
		if (item.getBurstTime() == 0) {
			text = String.format(
					"\033[38;2;%d;%d;%dm proses sonlandı        (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
					r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

			System.out.println(text);

			this.queue.remove(sayac);

			if (sayac == queue.size()) 
				sayac = 0;					
				
		} else { 
			text = String.format(
					"\033[38;2;%d;%d;%dm proses askıda          (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
					r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

			System.out.println(text);

			dl.TimeOut_Scanner(zaman + timer);

			sayac = (sayac + 1) % queue.size(); 

		}
		return timer;
	}
	
	
	
	
	
	
	
	
	
}
