import com.ideas2it.employee.view.EmployeeView;
import com.ideas2it.employee.view.ProjectView;

import java.util.Scanner;

/**
 * This the main class to iniate the programm.
 * @version 2.0. 13-09-2022.
 * @author  ESAKKIRAJA E.
 */
public class EmployeeManagementSystem {

    /**
     * Method used to call the method view to get the employee and Project details.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int portalOption;
        boolean isValid = false;
        do {
            System.out.println("*****APPLICATION PORTAL*****");
            System.out.println("\n1 - EMPLOYEE" + "\n2 - PROJECT" + "\n3 - EXIT");
            try {
                System.out.println("Enter the Application portal");
       
                portalOption = Integer.valueOf(scanner.nextLine());

                   switch(portalOption) {
                       case 1:
                           EmployeeView employeeView = new EmployeeView();
                           employeeView.chooseOption();
                           break;

                       case 2:
                           ProjectView projectView = new ProjectView();
                           projectView.chooseOption();
                           break;

                       case 3:
                           System.out.println("----- Thank you -----"); 
                           isValid = true;
                           break;

                       default:
                           System.out.println("Invalid input");
                   }
              } catch (NumberFormatException e) {
                  System.out.println("Invalid input");
              }
        } while(!isValid);
    }
}