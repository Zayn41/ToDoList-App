import java.util.*;

// ToDoList class
class ToDoList {
    String taskName;
    boolean isCompleted;

    // ToDoList constructor
    public ToDoList(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    // Getter and Setter methods
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean complete) {
        isCompleted = complete;
    }

    // Method to display tasks
    public void displayTask() {
        String status = isCompleted ? "Completed" : "pending";
        System.out.println(taskName + " - " + status);
    }
}

// ToDoList class handler
class ToDoHandler {
    HashMap<String, ToDoList> todoTasks; // using hashmap for optimisation.

    // ToDoHandler constructor
    public ToDoHandler() {
        this.todoTasks = new HashMap<>();
    }

    // Method to add tasks
    public void addTask(String taskName) {
        String key = taskName.toLowerCase();
        ToDoList newTask = new ToDoList(taskName);
        todoTasks.put(key, newTask);
        System.out.println("Task added: " + taskName);
    }

    // Method to remove tasks
    public void removeTask(String taskName) {
        String key = taskName.toLowerCase();
        if(todoTasks.containsKey(key)) {
            todoTasks.remove(key);
            System.out.println("Task removed: " + taskName);
        } else {
            System.out.println("Task not found!");
        }
    }

    // Method to display all tasks
    public void displayTasks() {
        if(todoTasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for(ToDoList task : todoTasks.values()) {
                task.displayTask();
            }
        }
    }

    // Method to mark task as completed
    public void markAsCompleted(String taskName) {
        String key = taskName.toLowerCase();
        ToDoList task = todoTasks.get(key);
        if(task != null) {
            task.setCompleted(true);
            System.out.println("Task marked as completed: " + task.getTaskName());
        } else {
            System.out.println("Task not found!");
        }
    }

    public void editTask(String oldTask, String newTask) {
        String oldKey = oldTask.toLowerCase();
        String newKey = newTask.toLowerCase();
        if(todoTasks.containsKey(oldKey)) {
            ToDoList task = todoTasks.remove(oldKey);
            task.setTaskName(newKey);
            todoTasks.put(newKey, task);
            System.out.println("Task renamed to: " + newTask);
        } else {
            System.out.println("Task not found!");
        }
    }

    public void showTaskSummary() {
        int compelete = 0;
        int pending = 0;
        for(ToDoList task : todoTasks.values()) {
            if(task.isCompleted()) {
                compelete++;
            } else {
                pending++;
            }
        }

        System.out.println("Completed: " + compelete + " | Pending: " + pending);
    }
}


public class TodoListApp {
    public static Scanner sc = new Scanner(System.in);

    // Function for userinput
    public static String getUserInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if(input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while(input.isEmpty());
        
        return input;
    }

    // Main function to start the app
    public static void startApp() {
        ToDoHandler todolist = new ToDoHandler();
        boolean running = true;

        while(running) {
            System.out.println("\n=== ToDoList App ===");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Display Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Edit Task");
            System.err.println("6. Show Task Summary");
            System.out.println("7. Exit");

            try {
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch(choice) {
                    case 1:
                        String taskName = getUserInput("Enter task name: ");
                        todolist.addTask(taskName);
                        break;
                    case 2:
                        String taskToRemove = getUserInput("Enter task name to remove: ");
                        todolist.removeTask(taskToRemove);
                        break;
                    case 3:
                        todolist.displayTasks();
                        break;
                    case 4:
                        String taskToMark = getUserInput("Enter task name to mark as completed: ");
                        todolist.markAsCompleted(taskToMark);
                        break;
                    case 5:
                        String oldTask = getUserInput("Enter task name to edit: ");
                        String newTask = getUserInput("Enter new task name: ");
                        todolist.editTask(oldTask, newTask);
                        break;
                    case 6:
                        todolist.showTaskSummary();
                        break;
                    case 7:
                        System.out.println("Thanks for using our ToDoList!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }
        
    }

    public static void main(String[] args) {
        startApp();
    }
}
