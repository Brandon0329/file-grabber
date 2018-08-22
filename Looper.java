import java.io.FileInputStream;
import java.util.List;

public class Looper implements Runnable {

	private FileInputStream fis;
	private List<Song> playList;
	private int index;
	
	public Looper(FileInputStream fis, List<Song> playList, int index) {
		this.fis = fis;
		this.playList = playList;
		this.index = index;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(fis.available());
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
