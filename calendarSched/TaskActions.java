package calendarSched;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TaskActions {

	Scanner s = new Scanner(System.in);

	public void displayListOfTasks(HashMap<String, Object> taskList) {
		if (taskList.size() > 0) {
			TreeMap<String, Object> sorted = new TreeMap<>(taskList);
			System.out.println("TASK LIST");
			System.out.println("================================");
			for (Map.Entry<String, Object> tl : sorted.entrySet()) {
				Task task1 = (Task) tl.getValue();
				System.out.println(tl.getKey());
				System.out.println("-Task Name: " + task1.getTaskName());
				System.out.println("-Start Date: " + task1.getStartDt());
				System.out.println("-End Date: " + task1.getEndDt());
				System.out.println("-Parent Task IDs: " + task1.getParent());
			}
		} else {
			System.out.println("No tasks found");
		}
	}

	public int displayMainSelection() {
		while (true) {
			System.out.println("--------------------------------");
			System.out.println("Select number to perform:");
			System.out.println("1 = Add Task");
			System.out.println("2 = View Task");
			System.out.println("0 = EXIT");

			String choice = s.next();

			if (isNumeric(choice)) {
				int i = Integer.parseUnsignedInt(choice);
				if (i >= 0 && i <= 2) {
					return i;
				}
				System.out.println("Select only from 0 to 2.");
			} else {
				System.out.println("Value not a number!");
			}
		}

	}

	public boolean isNumeric(String strNum) {
		try {
			int i = Integer.parseUnsignedInt(strNum);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public int displayQuestionWithValidation(String category, int... taskList) {
		String question = "";
		if (category.equals("DEPENDENCY")) {
			question = "Number of Parent Tasks ? ";
		} else {
			question = "Task Duration: ";
		}
		while (true) {
			System.out.print(question);
			String choiceStr = s.next();
			if (isNumeric(choiceStr)) {
				int i = Integer.parseUnsignedInt(choiceStr);
				if (category.equals("DEPENDENCY")) {
					if (i > taskList[0]) {
						System.out.println("Number of Task Dependencies is greater than the number of existing tasks.");
						continue;
					}
				}
				return i;
			} else {
				System.out.println("Enter a valid number!");
			}
		}
	}

	public HashMap<String, Object> calculateTaskDates(int dependency, int duration, HashMap<String, Object> taskList) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<String> parentTaskList = new ArrayList<String>();
		LocalDate tempDt = LocalDate.now();
		LocalDate startDt = null;
		LocalDate endDt = null;
		if (dependency > 0) {
			if (taskList.size() == 0) {
				System.out.println("No task to set as parent.");
				System.out.println("Number of Task Dependencies will be set to 0.");
				dependency = 0;
				startDt = LocalDate.now();
				endDt = startDt.plusDays(Long.valueOf(duration));
			} else {
				String parentTask = "";
				HashMap<String, Object> temp = new HashMap<String, Object>(taskList);
				for (int i = 0; i < dependency; i++) {
					System.out.println("Select parent task number " + (i + 1) + ": ");
					for (Map.Entry<String, Object> t : temp.entrySet()) {
						System.out.println(t.getKey().substring(5) + " = " + t.getValue());
					}
					parentTask = s.next();
					parentTaskList.add(parentTask);
					temp.remove("TASK " + parentTask);
				}

				System.out.println(taskList);

				for (String child : parentTaskList) {
					Task task = (Task) taskList.get("TASK " + child);
					if (task.getEndDt().compareTo(tempDt) > 0) {
						tempDt = task.getEndDt();
					}
				}
				startDt = tempDt.plusDays(Long.valueOf(1));
				endDt = startDt.plusDays(Long.valueOf(duration));

				String list = String.join(", ", parentTaskList);
				resultMap.put("parentTaskList", list);
			}
		} else {
			startDt = LocalDate.now();
			endDt = startDt.plusDays(Long.valueOf(duration));
		}
		resultMap.put("startDt", startDt);
		resultMap.put("endDt", endDt);

		return resultMap;
	}

}
