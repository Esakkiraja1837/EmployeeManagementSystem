package com.ideas2it.employee.util;

import java.util.regex.Pattern;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @version 1.0. 13-09-2022.
 * @author  ESAKKIRAJA E.
 */
public class ValidationUtil {


    public boolean validateJoiningDate(LocalDate dateOfBirth, LocalDate joiningDate){
        boolean isValid = true;

        LocalDate currentDate = LocalDate.now();
        int compareValue = currentDate.compareTo(joiningDate);
        if (!(compareValue >= 0)) {
            isValid = false;
        }

        if (18 >  Period.between(dateOfBirth, joiningDate).getYears()) {
            isValid = false;
        }
        return isValid;
    }

    public boolean validateDateOfBirth(LocalDate dateOfBirth) {
        boolean isValid= true;
        LocalDate currentDate = LocalDate.now();
        isValid = (18 <= Period.between(dateOfBirth, currentDate).getYears()
                   && Period.between(dateOfBirth, currentDate).
                   getYears() <= 60);

        return isValid;
    }

}

    
