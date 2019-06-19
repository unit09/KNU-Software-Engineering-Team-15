package systemUI;

import exchange.RecruitmentList;

public interface Observer {
	public abstract void update(RecruitmentList mainList);
}
