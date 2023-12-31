package İsletim;

import java.util.LinkedList;
import java.util.Random;


public class FirstPriorityList {

	private LinkedList<ProcessItems> queue;
	private SecondPriorityList spl;
	public FirstPriorityList() {
		this.queue = new LinkedList<ProcessItems>();
		this.spl = new SecondPriorityList();
	}
	
	public LinkedList<ProcessItems> getFirstPriorityList(){
		return this.queue;
	}
	public SecondPriorityList getFirstPriorityListspl() {
		return this.spl;
	}
	void FPL_add(ProcessItems item) {
		this.queue.addLast(item);

	}
	boolean FPL_isEmpty() {
		return this.queue.isEmpty();
	}
	int FPL_execute(int zaman) {

		Random rng = new Random();

		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		Dispatcher dl = ProcessReader.dl;

		String text = "";

		int timer = 0; 

		ProcessItems item = this.queue.remove();

		text = String.format(
				"\033[38;2;%d;%d;%dm proses basladı         (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
				r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

		System.out.println(text);

		timer++;
		item.setBurstTime(item.getBurstTime()-1);

		item.setSuspend(zaman + timer);
		if (item.getBurstTime() > 0) {

			item.setPriority(item.getPriority()+1);

			text = String.format(
					"\033[38;2;%d;%d;%dm proses askıda          (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
					r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

			System.out.println(text);

			spl.SPL_add(item);

		} else {
			text = String.format(
					"\033[38;2;%d;%d;%dm proses sonlandı         (id: %2d   oncelik:%2d   Mbayt:%2d   yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
					r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

			System.out.println(text);
		}

		dl.TimeOut_Scanner(zaman + timer); 

		return timer;
	}
}
