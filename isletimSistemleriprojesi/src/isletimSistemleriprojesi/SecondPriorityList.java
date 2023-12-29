package isletimSistemleriprojesi;

import java.util.LinkedList;
import java.util.Random;



public class SecondPriorityList {

	private LinkedList<process> queue ;
    private RoundRobinList rr;

    public SecondPriorityList(){
        this.queue = new LinkedList<process>();
        this.rr = new RoundRobinList();
    }
	public LinkedList<process> splqueueGetter(){
		return this.queue;
	}
	public RoundRobinList splrrGetter(){
		return this.rr;
	}

    public void splAdd(process item){
        this.queue.addLast(item);
    }

    boolean SPL_isEmpty() {
		return this.queue.isEmpty();
	}

    int SPL_execute(int zaman) {

		Random rng = new Random();

		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		Dispatcher dl = ProsesOkuma.dl;

		String text = "";

		int timer = 0;

		process item = this.queue.remove();

		text = String.format(
				"\033[38;2;%d;%d;%dm%2d sn proses basladi         (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m", r,
				g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

		System.out.println(text);

		timer++;
		item.burstTimeSetter(item.burstTimeGetter()-1);

		item.kesmeZamaniSetter(zaman + timer);

		if (item.burstTimeGetter() > 0) { 

			item.oncelikSetter(item.oncelikGetter()+1);

			rr.RR_add(item);       

			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses askida          (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

			System.out.println(text);

		} else {
			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

			System.out.println(text);
		}

		dl.TimeOut_Scanner((zaman + timer));

		return timer;
	}







}
