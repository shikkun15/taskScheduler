package calendarSched;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class TaskScheduler {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		System.out.println("START");

		HashMap<String, Object> taskList = new HashMap<String, Object>();
		TaskActions ta = new TaskActions();

		while (true) {
			int a = ta.displayMainSelection();
			System.out.println("--------------------------------");
			if (a == 1) {
				System.out.flush();
				int dependency = -1;
				int duration = -1;

				System.out.print("Task Name: ");
				String taskName = s.next();

				duration = ta.displayQuestionWithValidation("DURATION");
				dependency = ta.displayQuestionWithValidation("DEPENDENCY", taskList.size());

				HashMap<String, Object> taskDates = ta.calculateTaskDates(dependency, duration, taskList);

				LocalDate startDt = (LocalDate) taskDates.get("startDt");
				LocalDate endDt = (LocalDate) taskDates.get("endDt");
				String parent = dependency == 0 ? "0" : (String) taskDates.get("parentTaskList");
				Long id = Long.valueOf(taskList.size() + 1);

				System.out.println(">>>>> Adding task... >>>>>");
				Task t = new Task(id, taskName, startDt, endDt, parent);

				t.setId(Long.valueOf(taskList.size() + 1));
				if (dependency == 0) {
					t.setParent("0");
				} else {
					String list = (String) taskDates.get("parentTaskList");
					t.setParent(list);
				}

				System.out.println(">>>>> Task " + t.getTaskName() + " has been created. >>>>>");
				System.out.println(t.toString());
				taskList.put("TASK " + t.getId(), t);
			} else if (a == 2) {
				System.out.flush();
				ta.displayListOfTasks(taskList);
			} else {
				System.out.flush();
				System.out.println("Thank you for using the application.");
				System.out.println("END");
				break;
			}

		}

		s.close();
	}

}
