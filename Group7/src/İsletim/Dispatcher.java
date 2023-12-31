package İsletim;

import java.util.LinkedList;
import java.util.Random;


public class Dispatcher {
	LinkedList<ProcessItems> dispatchList;
	MemoryAllocator memoryAllocator;
	ResourcesAllocator rsrc;
	RealTimeQueue rtq;
	FirstPriorityList fpl;
	SecondPriorityList spl;
	RoundRobinList rr;
	UserJobQueue ujq;
	int damn_timer = 0;
	
	public Dispatcher() {
		dispatchList = new LinkedList<ProcessItems>();
		memoryAllocator = new MemoryAllocator(1024, 64);
		rsrc = new ResourcesAllocator();
		rtq = new RealTimeQueue();
		fpl = new FirstPriorityList();
		spl = fpl.getFirstPriorityListspl();
		rr = spl.getSecondPriorityListrr();
		ujq = new UserJobQueue();
	}
	
	public void AddList(ProcessItems item) {
		dispatchList.add(item);
	}
	
	public void Start_Dispatcher() {
		int size = dispatchList.size();

		int used_items_count = 0;

		while (used_items_count != size || !fpl.FPL_isEmpty() || !spl.SPL_isEmpty() || !rr.RR_isEmpty()) { // tüm
																											// boşaldığında
																											// duracak
			for (int i = 0; i < size; i++) {
				if (dispatchList.size() == 0)
					break;

				if (dispatchList.get(0).getArrival() > damn_timer) {
					break;
				} else if (dispatchList.get(0).getPriority() == 0) {
					if(dispatchList.get(0).getMbayt()>64){
						System.out.print(" id: "+ dispatchList.get(0).getId());
						System.out.println(" HATA - Gerçek-zamanlı proses (64MB) tan daha fazla bellek talep ediyor - proses silindi");
						dispatchList.remove();
						used_items_count++;
					}
					else if(dispatchList.get(0).getYazici()>2||dispatchList.get(0).getTarayici()>1||dispatchList.get(0).getModem()>1||dispatchList.get(0).getCD()>2){
						System.out.print(" id: " + dispatchList.get(0).getId());
						System.out.println(" HATA - Gerçek-zamanlı proses çok sayıda kaynak talep ediyor - proses silindi");
						dispatchList.remove();
						used_items_count++;
					}
					else{
						memoryAllocator.allocateMemory(dispatchList.element(), "bestfit");
						if(rsrc.rsrcChk(dispatchList.element())==true){
							rsrc.rsrcAlloc(dispatchList.element());
						}
						rtq.FCFS_add(dispatchList.remove());
						used_items_count++;
					}
				} else {
					if (dispatchList.get(0).getPriority() != 0) {
						ujq.UJQ_add(dispatchList.remove());
						used_items_count++;
					}
				}
			}

			if (!(ujq.getUserJobQueue().isEmpty()))
				ujq.UJ_Dispatch(memoryAllocator,rsrc);

			Executer(); 
		}
	}
	
	void Executer() {
		if (!(rtq.FCFS_isEmpty())) { 
			int rtq_ExecTime = rtq.FCFS_execute(damn_timer);
			damn_timer += rtq_ExecTime;
		} else if (!(fpl.FPL_isEmpty())) {
			int fpl_ExecTime = fpl.FPL_execute(damn_timer);
			damn_timer += fpl_ExecTime;
		} else if (!(spl.SPL_isEmpty())) {
			int spl_ExecTime = spl.SPL_execute(damn_timer);
			damn_timer += spl_ExecTime;
		} else if (!(rr.RR_isEmpty())) {
			int rr_ExecTime = rr.RR_execute(damn_timer);
			damn_timer += rr_ExecTime;
		} else
			damn_timer++;
	}
	
	
	
	public void TimeOut_Scanner(int gecenZaman) { 

		String text = "";

		for (int i = 0; i < rtq.getRealTimeQueue().size(); i++) {
			ProcessItems item = rtq.getRealTimeQueue().get(i);

			if (gecenZaman - item.getSuspend() >= 20)
			{
				Random rng = new Random();

				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm proses zamanasimi      (id: %2d   oncelik:%2d   Mbayt:%2d  yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
						r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

				System.out.println(text);

				rtq.getRealTimeQueue().remove(i);
				i--; 
			}
		}
		
		for (int i = 0; i < fpl.getFirstPriorityList().size(); i++) {
			ProcessItems item = fpl.getFirstPriorityList().get(i);
			if (gecenZaman - item.getSuspend() >= 20)
			{
				Random rng = new Random();
				
				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm proses zamanasimi      (id: %2d   oncelik:%2d   Mbayt:%2d  yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
						r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

				System.out.println(text);

				fpl.getFirstPriorityList().remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < spl.getSecondPriorityList().size(); i++) {
			ProcessItems item = spl.getSecondPriorityList().get(i);
			if (gecenZaman - item.getSuspend() >= 20)
			{
				Random rng = new Random();

				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm proses zamanasimi      (id: %2d   oncelik:%2d   Mbayt:%2d  yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
						r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

				System.out.println(text);

				spl.getSecondPriorityList().remove(i);
				i--;
			}
		}
		for (int i = 0; i < rr.getRoundRobinList().size(); i++) {
			ProcessItems item = rr.getRoundRobinList().get(i);
			if (gecenZaman - item.getSuspend() >= 20)
			{
				Random rng = new Random();

				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm proses zamanasimi      (id: %2d   oncelik:%2d   Mbayt:%2d  yazıcı:%2d   tarayıcı:%2d   modem:%2d   cd:%2d   kalan sure:%2d sn)\033[0m",
						r, g, b, item.getId(), item.getPriority(),item.getMbayt(),item.getYazici(),item.getTarayici(),item.getModem(),item.getCD(), item.getBurstTime());

				System.out.println(text);

				rr.getRoundRobinList().remove(i);
				i--;
			}
		}

	}
	
	
	
	
	
	
	
	
	
	
}
