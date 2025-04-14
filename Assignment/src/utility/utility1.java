/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Acer
 */
import adt.SortedDoublyLinkedList;
import adt.SortedListInterface;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class utility1 {

    public static int totalWidth = 160;

    public static String listToString(SortedListInterface<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            sb.append(list.viewDataAtIndex(i));
            if (i < list.getSize() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static String alignCenter(String text, int totalWidth) {
        // Calculate the number of spaces needed on each side to center-align the text
        int padding = (totalWidth - text.length()) / 2;
        int odd = (totalWidth - text.length()) % 2; // Check odd

        // Create a format string with padding on both sides
        String format = "%" + padding + "s%s%" + padding + "s";
        if (odd != 0) {
            format += " ";
        }

        // Use String.format to center-align the text
        return String.format(format, "", text, "");
    }

    public static void clearScreen() {
        try {
            Robot rob = new Robot();
            try {
                rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
                rob.keyPress(KeyEvent.VK_L); // press "L"
                rob.keyRelease(KeyEvent.VK_L); // unpress "L"
                rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
                Thread.sleep(100); // add delay in milisecond, if not there will automatically stop after clear
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
