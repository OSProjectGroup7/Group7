package isletimSistemleriprojesi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProsesOkuma {
	public static Dispatcher dl = null;

	public ProsesOkuma(Dispatcher list){
		ProsesOkuma.dl=list;
	}

	


	public void ProsesOku(String path) {
		try {
			File file = new File(path);
			if(file.exists()) {
				Scanner dosya = new Scanner(file);
				int sayac = 0;
				while (dosya.hasNextLine()) {
					String line = dosya.nextLine();
					line = line.replaceAll("\\s+", "");
					var bilgiler = line.split(",");
					
					int processId = sayac;
					int varisZamani = Integer.parseInt(bilgiler[0]);
					int oncelik = Integer.parseInt(bilgiler[1]);
					int processZamani = Integer.parseInt(bilgiler[2]);
					int Mbayt = Integer.parseInt(bilgiler[3]);
					int yazici = Integer.parseInt(bilgiler[4]);
					int tarayici = Integer.parseInt(bilgiler[5]);
					int modem = Integer.parseInt(bilgiler[6]);
					int CD = Integer.parseInt(bilgiler[7]);
					int kesmezamani = varisZamani;
					process proses = new process(processId,varisZamani,oncelik,processZamani,Mbayt,yazici,tarayici,modem,CD,kesmezamani);
					// kuyruk ekleme yeri
					dl.AddList(proses);


					
					sayac++;
				}
				dosya.close();
				dl.General_Dispatcher();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
