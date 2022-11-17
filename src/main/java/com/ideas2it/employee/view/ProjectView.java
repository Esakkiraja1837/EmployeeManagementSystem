package com.ideas2it.employee.view;

import com.ideas2it.employee.view.EmployeeView;
import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.ProjectController;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.util.ValidationUtil;
import com.ideas2it.employee.exception.EMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * This method used to get the details of the Project from the user
 * and set in the pojo class.
 * @version 1.0.
 * @author  ESAKKIRAJA E.
 */
public class ProjectView {

    Scanner scanner = new Scanner(System.in);
    ProjectController projectController = new ProjectController();
    ProjectDTO projectDTO = new ProjectDTO();
    EmployeeView employeeView = new EmployeeView();
    static Logger logger = LogManager.getLogger(ProjectView.class);

    /**
     * User to choose the CRUD options.
     */ 
    public void menuOption() {
        StringBuilder menuOption = new StringBuilder();
        menuOption.append("*****Projet Management***** \n\n")
                  .append("\n\t1 - CREATE \n")
                  .append("\t2 - DISPLAY \n\t3 - UPDATE \n\t4 - DELETE \n")
                  .append("\t5 - SEARCH \n\t\6 - EXIT \n");
        System.out.println(menuOption.toString());
    }

    /**
     *  method makes the user to choose the CRUD operation.
     */ 
    public void chooseOption() {
        boolean isExit = false;
        int option;

        do {
            menuOption();
            try {
                option = Integer.valueOf(scanner.nextLine());

                switch (option) {
                    case 1:
                        this.createProject();
                        break;

                    case 2:
                        this.displayProject();
                        break;

                    case 3:
                        this.updateProject();
                        break;

                    case 4:
                        deleteProject();
                        break;

                    case 5:
                        this.searchProject();
                        break;

                    case 6:
                        isExit = true;
                        System.out.println("----PROJECT PORTAL EXIT----");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid Input try again...");
            }

        }while (!isExit);
    }

    /**
     * Create and add Project details.
     * Get the Project values from the user.
     */
    public void createProject() {
        List<EmployeeDTO> employees = new ArrayList<>();
        boolean isCheckEmployee; 
        System.out.println(EmployeeManagementConstant.VALID_INPUT);
        try {
            int projectId = 0;
            String projectName = getProjectName();
            String domain = getDomain();
            String clientName = getClientName();
            String clientEmailId = getClientEmailId();
            LocalDate startDate = getStartDate();
            LocalDate dueDate = getDueDate();
            LocalDate endDate = getEndDate();
            isCheckEmployee = getResponse(); 

            if(isCheckEmployee) {
                employees = getEmployee(employees);
            }

            projectDTO = new ProjectDTO(projectName, domain, clientName,
                                        clientEmailId, startDate, dueDate, endDate, employees);

            projectId = projectController.addProject(projectDTO);

            logger.info("Project details stored");
            System.out.println("Project Deatils added");
        } catch (EMSException e) {
           logger.error("project Deatils not Created");
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }   
   }


    /**
     * Display the saved ProjectDetails.
     */
    public void displayProject() {
        try {
            List<ProjectDTO> projectDetail = projectController.getAllProject();

            if(!projectDetail.isEmpty()) {

                for (ProjectDTO projectDTO : projectDetail) {
                    System.out.println(projectDTO.toString());
                }
            } else {
                logger.info("Can not found the employeeDetails");
                System.out.println("Empty Data...");
            }
        } catch(EMSException e) {
            logger.error("Can not found the Project Details");
            System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * User to choose the update options.
     */ 
    public void displayUpdateMenu() {

        StringBuilder updateMenuOption = new StringBuilder();
        updateMenuOption.append("Project Details update - \n\n")
                        .append("\t1 - PROJECT NAME \n\t2 - DOMAIN \n")
                        .append("\t3 - CLIENT NAME \n\t4 - CLIENT EMAILID \n")
                        .append("\t5 - START DATE \n\t6 - DUE DATE \n")
                        .append("\t7 - END DATE \n\t8 - EMPLOYEE \n")
                        .append("\t9 - EXIT");
       System.out.println(updateMenuOption.toString());
    }

    public void updateProject() {
        boolean isValid = false;
        int projectId;
        int updateMenuOption = 0;
        ProjectDTO projectDto = null;
        try {
            do {
                projectId = getProjectId();
            } while(!(projectController.isProjectIdExists(projectId)));
            
            projectDto = projectController.getProjectIdPresent(projectId);

            if(null != projectDto) {

                do {
                    displayUpdateMenu();

                    try {
                        System.out.println("Enter the Project update option :");
                        updateMenuOption = Integer.valueOf(scanner.nextLine());

                        switch(updateMenuOption) {
                            case 1:
                                projectDto.setProjectName(getProjectName());
                                break;

                            case 2:
                                projectDto.setDomain(getDomain());
                                break;

                            case 3:
                                projectDto.setClientName(getClientName());
                                break;

                            case 4:
                                projectDto.setClientEmailId(getClientEmailId());
                                break;

                            case 5:
                                projectDto.setStartDate(getStartDate());
                                break;

                            case 6:
                                projectDto.setDueDate(getDueDate());
                                break;

                            case 7:
                                projectDto.setEndDate(getEndDate());
                                break;


                            case 8:
                                projectDto.setEmployee(getEmployee(projectDto.getEmployee()));
                                break;

                            case 9:
                                isValid = true;
                                break;

                            default:
                                System.out.println(EmployeeManagementConstant.
                                                   INVALID_INPUT);
                        }
                    } catch (NumberFormatException e) {
                           logger.error(EmployeeManagementConstant.
                                        INVALID_INPUT);
                           System.out.println(EmployeeManagementConstant.
                                              INVALID_INPUT);
                    }
                } while(!isValid);
                    projectController.updateProject(projectDto);
                    logger.info("Project Details Updated" + "ProjectID :" + projectId);
                   System.out.println("Project Details Updated");
            } else {
                System.out.println("ProjectId Not Exists");
            }
        
        } catch (EMSException e) {
            logger.error("Project Details can not updated");
            System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * Deletes Project by projectId.
     * If found delete that project object.
     * If the given projectId not found it shows out put as record not found.
     */
    public void deleteProject() {
        int projectId = getProjectId();

        try {

            if(projectController.isProjectIdExists(projectId)) {
                projectController.deleteProject(projectId);
                logger.info("Project Details Deleted");
                System.out.println("Project Details Deleted" + "project ID :" + projectId);
            } else {
                System.out.println("Project Details Not Exists" + "project ID :" + projectId);
            }
        } catch(EMSException e) {
           logger.error("Project Details Not found" + "Project ID :" + projectId);
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * Deletes Project by ProjectName,
     * If found Project Details.
     * If the given name not found it shows out put as record not found.
     */
    public void searchProject() {
        String projectName = getProjectName();
   
        try {
            List<ProjectDTO> project = projectController.searchProject(projectName);

            if(!project.isEmpty()) {

                for(ProjectDTO projectDTO : project) {
                    System.out.println(projectDTO.toString());
                }
            } else {
                logger.info("The Project Detail Not found" + "Project Name :" + projectName); 
                System.out.println("Project Details Not Found" + "project Name :" + projectName);
            }
        } catch (EMSException e) {
           logger.info("The Project Detail Not found" + "Project Name :" + projectName);
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * validate Project Id.
     * @return Return projectId.
     */
    public int getProjectId() {
        int projectId = 0;
        boolean isValid;

        do {
            System.out.println("Enter the ProjectID :");

            try {
                projectId = Integer.parseInt(scanner.nextLine());
                isValid = projectController.isValid(EmployeeManagementConstant.
                        REGEX_EMPLOYEEID,String.valueOf(projectId));

                if (!isValid) {
                    System.out.println(EmployeeManagementConstant.VALID_INPUT);
                }
            } catch (NumberFormatException e) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
                isValid = false;
            }
        } while (!isValid);
        return projectId;
    }

    /**
     * validate Project Name.
     * @return Return projectName.
     */
    public String getProjectName() {
        boolean isValid;
        String projectName;

        do {
            System.out.println("Enter the project Name :");
            projectName = scanner.nextLine();
            isValid = projectController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, projectName);

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
            }
        } while(!isValid);
        return projectName;
    }

    /**
     * validate Project Domain.
     * @return Return domain.
     */
    public String getDomain() {
        boolean isValid;
        String domain;

        do {
            System.out.println("Enter the Domain Name :");
            domain = scanner.nextLine();
            isValid = projectController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, domain);

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
            }
        } while (!isValid);
        return domain;
    }
    /**
     * validate Project ClientName.
     * @return Return clientName.
     */
    public String getClientName() {
        boolean isValid;
        String clientName;

        do {
            System.out.println("Enter the ClientName Name :");
            clientName = scanner.nextLine();
            isValid = projectController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, clientName);

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
            }
        } while(!isValid);
        return clientName;
    }

    /**
     * validate Project ClientEmailId.
     * @return Return EmailId.
     */
    public String getClientEmailId() {
        boolean isValid = false;
        String clientEmailId = null;

        do {

                System.out.println("Enter the ClientEmailId :");
                clientEmailId = scanner.nextLine();
                isValid = projectController.isValid(EmployeeManagementConstant
                                                     .REGEX_EMAILID, clientEmailId);
                if(!isValid) {
                    System.out.println(EmployeeManagementConstant.INVALID_DATA);
                }
         } while (!isValid);
         return clientEmailId;
    }

    /**
     * validate Project startDate.
     * @return Return startDate.
     */
    public LocalDate getStartDate() {
        LocalDate startDate = null;
        boolean isValid;

        do {
           System.out.println("Enter the project Start Date(yyyy-mm-dd) :");
           try {
               startDate = LocalDate.parse(scanner.nextLine());
               isValid = true;
           } catch(DateTimeException e) {
               System.out.println(EmployeeManagementConstant.INVALID_INPUT);
               isValid = false;
           }
        } while (!isValid);
        return startDate;
    }

    /**
     * validate Project dueDate.
     * @return Return dueDate.
     */
    public LocalDate getDueDate() {
        LocalDate dueDate = null;
        boolean isValid;

        do {
           System.out.println("Enter the project Due Date(yyyy-mm-dd) :");
           try {
               dueDate = LocalDate.parse(scanner.nextLine());
               isValid = true;
           } catch(DateTimeException e) {
               System.out.println(EmployeeManagementConstant.INVALID_INPUT);
               isValid = false;
           }
        } while (!isValid);
        return dueDate;
    }

    /**
     * validate Project endDate.
     * @return Return endDate.
     */
    public LocalDate getEndDate() {
        LocalDate endDate = null;
        boolean isValid;
        do {
            System.out.println("Enter the project End Date(yyyy-mm-dd) :");
            try {
                endDate = LocalDate.parse(scanner.nextLine());
                isValid = true;
            } catch(DateTimeParseException e) {
                System.out.println(EmployeeManagementConstant.INVALID_INPUT);
                isValid = false;
            }
        } while(!isValid);
        return endDate;
    }

    /**
     * get response from user to assign the Project for employee
     * @return Return value.
     */
    public boolean getResponse() {
        boolean isCheckEmployee = false;
        boolean isValid;
        System.out.println("If you want add Employee press y for Yes/ n for No");
        String option;

        do {
            isValid = true;
            option = scanner.nextLine();

            switch(option) {
                case "y":
                    isCheckEmployee = true;
                    break;

                case "n":
                    isCheckEmployee = false;
                    break;

                default:
                    isValid = false;
                    System.out.println("Invalid input");
            }
        } while(!isValid);
        return isCheckEmployee;
    }

    /**
     * check Employee exists or not.
     * @return Return employees.
     */
    public List<EmployeeDTO> getEmployee(List<EmployeeDTO> employees) {
        EmployeeDTO employeeDTO = null;
        boolean isCheckEmployee;
        try {
            do {
                employeeDTO = projectController.getEmployee(getEmployeeId());

                if(null != employeeDTO) {
                    employees.add(employeeDTO);
                }
                isCheckEmployee = getResponse();
            } while(isCheckEmployee);
        } catch(EMSException e) {
            System.out.println("Invalid input...");
        }
        return employees;
    }

    /**
     * check EmployeeId exists or not.
     * @return Return employeeId.
     */
    public int getEmployeeId() {
        boolean isValid = false;
        int employeeId;
        System.out.println("Enter the employeeId :");

        do {
            employeeId = Integer.parseInt(scanner.nextLine());

                if (projectController.isValid(EmployeeManagementConstant.
                        REGEX_EMPLOYEEID,String.valueOf(employeeId))) {
                    isValid = true;
                } else {
                    System.out.println("Invalid Employee ID... try again");
                }
        } while(!isValid);
        return employeeId;
    }
}