package İsletim;

import java.util.LinkedList;
import java.util.Random;


public class RealTimeQueue {

	private LinkedList<ProcessItems> queue;
	
	public RealTimeQueue() {
		this.queue = new LinkedList<ProcessItems>();
	}
	public LinkedList<ProcessItems> getRealTimeQueue(){
		return this.queue;
	}
	void FCFS_add(ProcessItems item) {
		this.queue.addLast(item);

	}
	boolean FCFS_isEmpty() {
		return this.queue.isEmpty();
	}
	int FCFS_execute(int zaman) {

		Random rng = new Random();

		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		Dispatcher dl = ProcessReader.dl;

		int timer = 0;

		String text = "";

		ProcessItems item =this.queue.remove();

		
		text = String.format(
				"\033[38;2;%d;%d;%dm proses basladı         (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
				r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

		System.out.println(text);

		while (item.getBurstTime() != 0) {
			item.setBurstTime(item.getBurstTime()-1);
			timer++;

			item.setSuspend(timer + zaman); 

			if (item.getBurstTime() > 0) {

				text = String.format(
						"\033[38;2;%d;%d;%dm proses yurutuluyor     (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
						r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

				System.out.println(text);
			} else {
				text = String.format(
						"\033[38;2;%d;%d;%dm proses sonlandı        (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
						r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

				System.out.println(text);
			}
			dl.TimeOut_Scanner(zaman + timer); 

		}

		return timer;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
