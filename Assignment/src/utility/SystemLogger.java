/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author user
 */

import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SystemLogger {
    private static final SortedListInterface<String> logs = new SortedDoublyLinkedList<>();

    public static void log(String action) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = "[LOG] " + timestamp + " - " + action;
        logs.addAtFront(logEntry);
    }

    public static SortedListInterface<String> getLogs() {
        return logs;
    }

    public static void printLogs() {
        System.out.println(utility1.alignCenter("==== System Activity Logs (Latest on Top) ====", utility1.totalWidth));
        for (int i = 0; i < logs.getSize(); i++) {
            System.out.println(utility1.alignCenter(logs.viewDataAtIndex(i), utility1.totalWidth));
        }
        System.out.println(utility1.alignCenter("==============================================", utility1.totalWidth));
    }
}

