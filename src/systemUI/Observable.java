package systemUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import client.Client;
import exchange.RecruitmentList;

public class Observable extends JFrame {
	private static ArrayList<Observer> observers = new ArrayList<Observer>();
	protected static RecruitmentList mainList;
	protected static Client client;
	
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
	
	public static void downloadData() {
		mainList = (RecruitmentList)client.getObject("RecruitmentList");
	}
	
	public static void uploadData() {
		client.setObject("RecruitmentList", mainList);
	}
	
	public Observable(String name) {
		super(name);
	}
}
