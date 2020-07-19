package calendarSched;

import java.time.LocalDate;

public class Task {

	private Long id;
	private String taskName;
	private LocalDate startDt;
	private LocalDate endDt;
	private String parent;

	public Task(Long id, String taskName, LocalDate startDt, LocalDate endDt, String parent) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.startDt = startDt;
		this.endDt = endDt;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDate getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}

	public LocalDate getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDate endDt) {
		this.endDt = endDt;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		String s = "********************************\n";
		s += "TASK " + id + "\n-Task Name: " + taskName + "\n-Start Date: " + startDt + "\n-End Date: " + endDt
				+ "\n-Parent Task: " + parent + "\n";
		s += "********************************";
		return s;
	}

}
