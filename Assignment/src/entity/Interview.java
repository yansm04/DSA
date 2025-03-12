/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class Interview {

    private String interviewID;
    private int hour;
    private int minute;
    private String period;
    private String feedback;
    private String result;
    private String type;

    public Interview(String interviewID, int hour, int minute, String period, String feedback, String result, String type) {
        this.interviewID = interviewID;
        this.hour = hour;
        this.minute = minute;
        this.period = period;
        this.feedback = feedback;
        this.result = result;
        this.type = type;
    }

    public String getInterviewID() {
        return interviewID;
    }

    public void setInterviewID(String interviewID) {
        this.interviewID = interviewID;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Interview ID: " + interviewID
                + ", Time: " + String.format("%02d:%02d %s", hour, minute, period)
                + ", Type: " + type
                + ", Result: " + result
                + ", Feedback: " + feedback;
    }

}
