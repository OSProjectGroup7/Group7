package İsletim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProcessReader { //txt dosyasından okuma sınıfı.

	public static Dispatcher dl;
	
	public ProcessReader(Dispatcher list) {
		ProcessReader.dl= list;
	}
	
	
	
	public void processread() throws FileNotFoundException {
		String path = "./giris.txt";
		File file = new File(path);
			if(file.exists()) {
				Scanner dosya = new Scanner(file);
				int sayac = 0;
				while (dosya.hasNextLine()) {
					ProcessItems item = new ProcessItems();
					
					String line = dosya.nextLine();
					line = line.replaceAll("\\s+", "");
					var bilgiler = line.split(",");
					
					item.setId(sayac); 
					item.setArrival(Integer.parseInt(bilgiler[0])); 
					item.setPriority(Integer.parseInt(bilgiler[1])); 
					item.setBurstTime(Integer.parseInt(bilgiler[2])); 
					item.setMbayt(Integer.parseInt(bilgiler[3])); 
					item.setYazici(Integer.parseInt(bilgiler[4])); 
					item.setTarayici(Integer.parseInt(bilgiler[5])); 
					item.setModem(Integer.parseInt(bilgiler[6])); 
					item.setCD(Integer.parseInt(bilgiler[7]));
					item.setSuspend(Integer.parseInt(bilgiler[0]));
					// kuyruk ekleme yeri
					dl.AddList(item);


					
					sayac++;
				}
				dosya.close();
				dl.Start_Dispatcher();
			}
	}
}
