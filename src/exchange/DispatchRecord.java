package exchange;

import java.io.Serializable;
import java.util.*;

public class DispatchRecord implements Serializable {
	private String nation;
	private String university;
	private int startYear;
	private int startSemester;
	private int period;
	private String major;
	
	public DispatchRecord(String nation, String university, int startYear, int startSemester, int period, String major){
		this.nation = nation;
		this.university = university;
		this.startYear = startYear;
		this.startSemester = startSemester;
		this.period = period;
		this.major = major;
	}
	
	public String getNation() {
		return nation;
	}
	public String getUniversity() {
		return university;
	}
	public int getStartYear() {
		return startYear;
	}
	public int getStartSemester() {
		return startSemester;
	}
	public int getPeriod() {
		return period;
	}
	public String getMajor() {
		return major;
	}
}
