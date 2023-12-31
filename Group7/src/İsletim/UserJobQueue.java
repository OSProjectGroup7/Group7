package İsletim;

import java.util.LinkedList;

public class UserJobQueue { //önceliği 0 hariç olan proseslerin tutulduğu kuyruk.
	private LinkedList<ProcessItems> queue;
	
	public UserJobQueue(){
		this.queue = new LinkedList<ProcessItems>();
	}
	public LinkedList<ProcessItems> getUserJobQueue(){
		return this.queue;
	}
	void UJQ_add(ProcessItems item) {
		this.queue.addLast(item);
	}
	
	void UJ_Dispatch(MemoryAllocator allocator, ResourcesAllocator rsrc)
	{
		Dispatcher dl = ProcessReader.dl;
		
		while(!this.queue.isEmpty())
		{
			if (this.queue.get(0).getPriority() == 1) {
				if(queue.get(0).getMbayt()>960){
					System.out.print(" id: "+  queue.get(0).getId());
					System.out.println(" HATA - Proses (960MB) tan daha fazla bellek talep ediyor - proses silindi");
					queue.remove();
				}
				else if(queue.get(0).getYazici()>2||queue.get(0).getTarayici()>1||queue.get(0).getModem()>1||queue.get(0).getCD()>2){
					System.out.print(" id: " + queue.get(0).getId());
					System.out.println(" HATA - Proses çok sayıda kaynak talep ediyor - proses silindi");
					queue.remove();
	
				}
				else{
					allocator.allocateMemory(queue.element(), "bestfit");
					if(rsrc.rsrcChk(queue.element())==true){
						rsrc.rsrcAlloc(queue.element());
					}
					dl.fpl.FPL_add(this.queue.remove());
				}
				
			} else if (this.queue.get(0).getPriority() == 2) {
				if(queue.get(0).getMbayt()>960){
					System.out.print(" id: " + queue.get(0).getId());
					System.out.println(" HATA - Proses (960MB) tan daha fazla bellek talep ediyor - proses silindi");
					queue.remove();
	
				}
				else if(queue.get(0).getYazici()>2||queue.get(0).getTarayici()>1||queue.get(0).getModem()>1||queue.get(0).getCD()>2){
					System.out.print(" id: "+ queue.get(0).getId());
					System.out.println(" HATA - Proses çok sayıda kaynak talep ediyor - proses silindi");
					queue.remove();
	
				}
				else{
					allocator.allocateMemory(queue.element(), "bestfit");
					if(rsrc.rsrcChk(queue.element())==true){
						rsrc.rsrcAlloc(queue.element());
					}
					dl.spl.SPL_add(this.queue.remove());
				}
				
			} else {
				if(queue.get(0).getMbayt()>960){
					System.out.print(" id: " + queue.get(0).getId());
					System.out.println(" HATA - Proses (960MB) tan daha fazla bellek talep ediyor - proses silindi");
					queue.remove();
	
				}
				else if(queue.get(0).getYazici()>2||queue.get(0).getTarayici()>1||queue.get(0).getModem()>1||queue.get(0).getCD()>2){
					System.out.print(" id: "+ queue.get(0).getId());
					System.out.println(" HATA - Proses çok sayıda kaynak talep ediyor - proses silindi");
					queue.remove();
	
				}
				else{
					allocator.allocateMemory(queue.element(), "bestfit");
					if(rsrc.rsrcChk(queue.element())==true){
						rsrc.rsrcAlloc(queue.element());
					}
					dl.rr.RR_add(this.queue.remove());
				}
				
			}
		}
		
	}
}
