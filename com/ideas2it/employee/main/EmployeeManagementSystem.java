package com.ideas2it.employee.main;

import com.ideas2it.employee.view.EmployeeView;

/**
 * This the main class to iniate the programm.
 * @version 2.0. 13-09-2022.
 * @author  ESAKKIRAJA E.
 */
public class EmployeeManagementSystem {

    /**
     * Method used to call the method view to get the employee details.
     */
    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        employeeView.chooseOption();
    }
}