package isletimSistemleriprojesi;

import java.util.LinkedList;
import java.util.Random;


public class RoundRobinList {

	  private LinkedList<process> queue ;
	    

	    public RoundRobinList(){
	        this.queue = new LinkedList<process>();
	    }
		
		public LinkedList<process> rrqueueGetter(){
			return 	this.queue;
		}
	    void RR_add(process item) {
			this.queue.addLast(item);
		}

	    boolean RR_isEmpty() {
			return this.queue.isEmpty();
		}

	   // process cikanEleman = null;

	    int RR_execute(int zaman) {

	    	int sayac = 0;
			Random rng = new Random();

			int r = rng.nextInt(256);
			int g = rng.nextInt(256);
			int b = rng.nextInt(256);

			Dispatcher dl = ProsesOkuma.dl;

			String text = "";

			int timer = 0; 
			process item = queue.get(sayac);

			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses basladi         (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m", r,
					g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

			System.out.println(text);

			timer++;
			item.burstTimeSetter(item.burstTimeGetter()-1);

			item.kesmeZamaniSetter(zaman + timer);
			if (item.burstTimeGetter() == 0) {
				text = String.format(
						"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);

				this.queue.remove(sayac);

				if (sayac == this.queue.size()) 
					sayac = 0;					
					
			} else { 
				text = String.format(
						"\033[38;2;%d;%d;%dm%2d sn proses askida          (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);

				dl.TimeOut_Scanner(zaman + timer);

				sayac = (sayac + 1) % this.queue.size(); 

			}
			return timer;
		}
	    //deneme

}
