package com.ideas2it.employee.util;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * 
 * @version 1.0. 13-09-2022.
 * @author  ESAKKIRAJA E.
 */
public class EmployeeUtil {

    Scanner scanner = new Scanner(System.in);

    public LocalDate getDate() {

        String date =scanner.next();
        LocalDate dateOfJoining = LocalDate.parse(date);
        return dateOfJoining;
    }

    public LocalDate getDate() {

        String date =scanner.next();
        LocalDate dateOfBirth = LocalDate.parse(date);
        return dateOfBirth;
    }
}

    