package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InterviewResult implements Comparable<InterviewResult> {

    private Interview interview;
    private int totalScore;
    private LocalDateTime resultTime;

    public InterviewResult() {
        this.interview = null;
        this.totalScore = 0;
        this.resultTime = null;
    }

    public InterviewResult(Interview interview, int totalScore) {
        this.interview = interview;
        this.totalScore = totalScore;
        this.resultTime = LocalDateTime.now();
    }

    public Interview getInterview() {
        return interview;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public LocalDateTime getResultTime() {
        return resultTime;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setResultTime(LocalDateTime resultTime) {
        this.resultTime = resultTime;
    }

    @Override
    public int compareTo(InterviewResult other) {
        // newest results first
        return other.resultTime.compareTo(this.resultTime);
    }

    @Override
    public String toString() {
        return String.format("%s | Total: %d | At: %s",
                interview.getInterviewID(),
                totalScore,
                resultTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
