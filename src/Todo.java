import java.util.Scanner;

public class Todo {
    private Task[] tasks = new Task[100];
    private final Scanner scanner = new Scanner(System.in);
    private int tasksCount = 0;
    private int menuInput = 0;

    public void start() {
        while (menuInput != 5) {
            System.out.println("Меню:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Удалить задачу (по номеру)");
            System.out.println("4. Отметить задачу как выполненную");
            System.out.println("5. Выход\n");

            System.out.println("Выберите пункт меню:");

            checkIfInt();

            menuInput = scanner.nextInt();
            scanner.nextLine();
            switch (menuInput) {
                case 1 -> addTask();
                case 2 -> showAllTasks();
                case 3 -> deleteTask();
                case 4 -> markAsDone();
                case 5 -> {
                    System.out.println("До новых встреч!");
                }
                default -> System.out.println("Такого пункта меню не существует!");
            }
        }
    }

    private void addTask() {
        if (tasksCount >= tasks.length) {
            System.out.println("Список задач переполнен!");
            return;
        }
        System.out.println("Введите описание задачи:");
        String task = scanner.nextLine();
        tasks[tasksCount] = new Task(tasksCount, task);
        System.out.println("Задача добавлена!");
        tasksCount += 1;
    }

    private void showAllTasks() {
        if (isTaskListEmpty()) {
            System.out.println("Ваш список задач пуст!");
            return;
        }
        System.out.println("Список задач:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println(tasks[i].toString());
        }
    }

    private void deleteTask() {
        if (isTaskListEmpty()) {
            System.out.println("Ваш список задач пуст!");
            return;
        }

        System.out.println("Введите номер задачи для удаления:");

        checkIfInt();

        int taskToDeleteNumber = scanner.nextInt();

        if (taskToDeleteNumber <= 0 || taskToDeleteNumber > tasksCount) {
            System.out.println("Задача с таким номером не существует!");
            return;
        }

        int taskToDeleteIndex = taskToDeleteNumber - 1;
        Task taskToDelete = tasks[taskToDeleteIndex];
        Task[] tasksArrayNew = new Task[100];

        int j = 0;
        for (int i = 0; i < tasksCount; i++) {
            if (i == taskToDeleteIndex) {
                continue;
            }
            tasks[i].setOrder(j);
            tasksArrayNew[j] = tasks[i];
            j++;
        }

        tasksCount--;
        System.out.println("Задача " + "'" + taskToDelete.getDescription() + "'" + " удалена!");
        tasks = tasksArrayNew;
    }


    private void markAsDone() {
        if (isTaskListEmpty()) {
            System.out.println("Ваш список задач пуст!");
            return;
        }

        System.out.println("Введите номер задачи для отметки:");

        checkIfInt();

        int completedTaskNumber = scanner.nextInt();

        if (completedTaskNumber <= 0 || completedTaskNumber > tasksCount) {
            System.out.println("Задача с таким номером не существует!");
            return;
        }

        int taskToCompleteIndex = completedTaskNumber - 1;
        Task taskToComplete = tasks[taskToCompleteIndex];

        if (taskToComplete.isDone()) {
            System.out.println("Вы уже выполнили эту задачу!");
            return;
        }

        taskToComplete.setDone(true);
        System.out.println("Задача " + "'" + taskToComplete.getDescription() + "'" + " отмечена как выполненная!");
    }

    private boolean isTaskListEmpty() {
        return tasksCount == 0;
    }

    private void checkIfInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите цифру!");
            scanner.next();
        }
    }
}