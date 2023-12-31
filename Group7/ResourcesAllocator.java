package İsletim;

public class ResourcesAllocator {
	 Rsrc resources = new Rsrc(2, 1, 1, 2);

	    public boolean rsrcChk(ProcessItems claim) { //kaynakların uygun olup  olmadığını kontrol eder.
	        if (resources == null || claim == null) return false;
	        
	        return resources.getPrinters() >= claim.getYazici() &&
	               resources.getScanners() >= claim.getTarayici() &&
	               resources.getModems() >= claim.getModem() &&
	               resources.getCds() >= claim.getCD();
	    }
	   

	    
	    public boolean rsrcAlloc(ProcessItems claim) { //Kaynak yönetiminin sağlandığı kısım.
	        if (!rsrcChk(claim)) return false;
	        
	        resources.setPrinters(resources.getPrinters() - claim.getYazici());
	        resources.setScanners(resources.getScanners() - claim.getTarayici());
	        resources.setModems(resources.getModems() - claim.getModem());
	        resources.setCds(resources.getCds() - claim.getCD());
	        rsrcFree(claim);
	        return true;
	    }

	    public void rsrcFree(ProcessItems claim) { //Kaynakları serbest bırakır.
	        if (resources == null || claim == null) return;

	        resources.setPrinters(resources.getPrinters() + claim.getYazici());
	        resources.setScanners(resources.getScanners() + claim.getTarayici());
	        resources.setModems(resources.getModems() + claim.getModem());
	        resources.setCds(resources.getCds() + claim.getCD());
	    }
}
