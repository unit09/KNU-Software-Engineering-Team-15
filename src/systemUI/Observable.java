package systemUI;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import exchange.RecruitmentList;

public class Observable extends JFrame {
	private static Vector observers = new Vector();
	private static RecruitmentList mainList;
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public static void notifyObservers() {
		Iterator it = observers.iterator();
		while(it.hasNext()) {
			Observer o = (Observer)it.next();
			o.update(mainList);
		}
	}
	
	
}
