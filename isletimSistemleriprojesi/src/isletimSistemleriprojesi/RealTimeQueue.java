package isletimSistemleriprojesi;

import java.util.LinkedList;
import java.util.Random;


public class RealTimeQueue {

	private LinkedList<process> queue ;

    public RealTimeQueue(){
        this.queue = new LinkedList<process>();
    }

	public LinkedList<process> queueGetter(){
		return this.queue;
	}

    void FCFS_add(process item) {
		this.queue.addLast(item);
	}

    boolean FCFS_isEmpty() {
		return this.queue.isEmpty();
	}

    int FCFS_execute(int zaman) {
		int timer = 0;
		String text = "";

		Random rng = new Random();

		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		Dispatcher dl = ProsesOkuma.dl;


		process item =this.queue.remove();

		
		text = String.format(
				"\033[38;2;%d;%d;%dm%2d sn proses basladi         (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m", r,
				g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

		System.out.println(text);

		while (item.burstTimeGetter() != 0) {
			item.burstTimeSetter(item.burstTimeGetter()-1);
			timer++;

			item.kesmeZamaniSetter(zaman + timer); 

			if (item.burstTimeGetter() > 0) {

				text = String.format(
						"\033[38;2;%d;%d;%dm%2d sn proses yurutuluyor     (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);
			} else {
				text = String.format(
						"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);
			}
			dl.TimeOut_Scanner(zaman + timer); 

		}

		return timer;
	}
}
