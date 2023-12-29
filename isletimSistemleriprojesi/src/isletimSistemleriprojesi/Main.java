package isletimSistemleriprojesi;

//deneme

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dispatcher dispatcher = new Dispatcher();
		ProsesOkuma oku = new ProsesOkuma(dispatcher);
		oku.ProsesOku("./giris.txt");
		
	}

}
