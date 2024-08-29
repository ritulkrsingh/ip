public class Event extends Task {
    private String timeL;
    private String timeR;

    public Event(String description, String timeL, String timeR) {
        super(description);
        this.timeL = timeL;
        this.timeR = timeR;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + timeL + " to: " + timeR + ")";
    }   
}
