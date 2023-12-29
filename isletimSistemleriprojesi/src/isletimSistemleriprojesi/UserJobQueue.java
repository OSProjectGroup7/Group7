package isletimSistemleriprojesi;

import java.util.LinkedList;


public class UserJobQueue {

	private LinkedList<process> List;

	public UserJobQueue(){
		this.List = new LinkedList<process>();
	}

	public LinkedList<process> UserJobQueueGetter(){
		return this.List;
	}

	public void UserJobQueueAdd(process item){
		if(this.List.size()==0){
			this.List.addLast(item);
		}
	}
	
	public void UserJobQueueDispatcher(){
		Dispatcher dl = ProsesOkuma.dl;
		
		
		while(!this.List.isEmpty())
		{
			if (this.List.get(0).oncelikGetter() == 1) {
				dl.fplGetter().fplAdd(this.List.remove());
			} else if (this.List.get(0).oncelikGetter() == 2) {
				dl.splGetter().splAdd(this.List.remove());
			} else {
				dl.rrGetter().RR_add(this.List.remove());
			}
		}

	}
}
