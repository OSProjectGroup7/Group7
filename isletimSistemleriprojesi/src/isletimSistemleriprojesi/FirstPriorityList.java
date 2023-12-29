package isletimSistemleriprojesi;

import java.util.LinkedList;
import java.util.Random;


public class FirstPriorityList {

	private LinkedList<process> queue ;
	private SecondPriorityList spl;

    public FirstPriorityList(){
        this.queue = new LinkedList<process>();
		this.spl = new SecondPriorityList();
    }
	public SecondPriorityList fplsplGetter(){
		return this.spl;
	}
	public LinkedList<process> fplqueueGetter(){
		return this.queue;
	}
    public void fplAdd(process item){
        this.queue.add(item);
    }

    public boolean fplisEmpty(){
        return this.queue.isEmpty();
    }

    int FPL_execute(int zaman) {
    	String text = "";
		int timer = 0; 
		Random rng = new Random();

		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		Dispatcher dl = ProsesOkuma.dl;

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

			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses askida          (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

			System.out.println(text);

			this.spl.splAdd(item);

		} else {
			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

			System.out.println(text);
		}

		dl.TimeOut_Scanner(zaman + timer); 

		return timer;
	}

}
