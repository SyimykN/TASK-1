public class Task {
    private int order;
    private boolean isDone;
    private String description;

    public Task(int order, String description) {
        this.isDone = false;
        this.order = order;
        this.description = description;
    }

    @Override
    public String toString() {
        String status = isDone ? "✅ " : "❌ ";
        return (order + 1) + ". " + status + description;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}