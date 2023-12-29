package isletimSistemleriprojesi;

import java.util.LinkedList;
import java.util.Random;


public class Dispatcher {
	private LinkedList<process> dispatchList;
	private UserJobQueue ujq;
	private RealTimeQueue rtq;
	private FirstPriorityList fpl;
	private SecondPriorityList spl;
	private RoundRobinList rr;
	private int timerr = 0;

	public Dispatcher() {
		this.dispatchList = new LinkedList<process>();
		this.ujq = new UserJobQueue();
		this.rtq = new RealTimeQueue();
		this.fpl = new FirstPriorityList();
		this.spl = this.fpl.fplsplGetter();
		this.rr = this.spl.splrrGetter();
	}
	public FirstPriorityList fplGetter(){
		return this.fpl;
	}
	public SecondPriorityList splGetter(){
		return this.spl;
	}
	public RoundRobinList rrGetter(){
		return this.rr;
	}
	public void AddList(process item) {
		this.dispatchList.add(item);
	}
	
	public void General_Dispatcher() {
		int size = this.dispatchList.size();

		int used_items_count = 0;

		while (used_items_count != size || !this.fpl.fplisEmpty() || !this.spl.SPL_isEmpty() || !this.rr.RR_isEmpty()) { // tüm
																											// boşaldığında
																											// duracak
			for (int i = 0; i < size; i++) {
				if (this.dispatchList.size() == 0)
					break;

				if (this.dispatchList.get(0).varisZamaniGetter() > timerr) {
					break;
				} else if (this.dispatchList.get(0).oncelikGetter() == 0) {
					rtq.FCFS_add(this.dispatchList.remove());
					used_items_count++;
				} else {
					if (this.dispatchList.get(0).oncelikGetter() != 0) {
						this.ujq.UserJobQueueAdd(this.dispatchList.remove());
						used_items_count++;
					}
				}
			}

			if (!(this.ujq.UserJobQueueGetter().isEmpty()))
				this.ujq.UserJobQueueDispatcher();

			Executer(); 
		}
	}

	void Executer() {
		if (!(this.rtq.FCFS_isEmpty())) { 
			int rtq_ExecTime = this.rtq.FCFS_execute(timerr);
			timerr += rtq_ExecTime;
		} else if (!(this.fpl.fplisEmpty())) {
			int fpl_ExecTime = this.fpl.FPL_execute(timerr);
			timerr += fpl_ExecTime;
		} else if (!(this.spl.SPL_isEmpty())) {
			int spl_ExecTime = this.spl.SPL_execute(timerr);
			timerr += spl_ExecTime;
		} else if (!(this.rr.RR_isEmpty())) {
			int rr_ExecTime = this.rr.RR_execute(timerr);
			timerr += rr_ExecTime;
		} else
			timerr++;
	}


	public void TimeOut_Scanner(int gecenZaman) { 

		String text = "";

		for (int i = 0; i < this.rtq.queueGetter().size(); i++) {
			process item = this.rtq.queueGetter().get(i);

			if (gecenZaman - item.kesmeZamaniGetter() >= 20)
			{
				Random rng = new Random();

				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);

				this.rtq.queueGetter().remove(i);       
				i--; 
			}
		}
		
		for (int i = 0; i < this.fpl.fplqueueGetter().size(); i++) {
			process item = this.fpl.fplqueueGetter().get(i);
			if (gecenZaman - item.kesmeZamaniGetter() >= 20)
			{
				Random rng = new Random();
				
				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);

				this.fpl.fplqueueGetter().remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < this.spl.splqueueGetter().size(); i++) {
			process item = this.spl.splqueueGetter().get(i);
			if (gecenZaman - item.kesmeZamaniGetter() >= 20)
			{
				Random rng = new Random();

				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);

				this.spl.splqueueGetter().remove(i);
				i--;
			}
		}
		for (int i = 0; i < this.rr.rrqueueGetter().size(); i++) {
			process item = this.rr.rrqueueGetter().get(i);
			if (gecenZaman - item.kesmeZamaniGetter() >= 20)
			{
				Random rng = new Random();

				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.processIdGetter(), item.oncelikGetter(), item.burstTimeGetter());

				System.out.println(text);

				this.rr.rrqueueGetter().remove(i);
				i--;
			}
		}

	}








}
