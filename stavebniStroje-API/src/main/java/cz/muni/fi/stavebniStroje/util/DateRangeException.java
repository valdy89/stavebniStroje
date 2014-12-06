/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.util;

/**
 *
 * @author spito
 */
public class DateRangeException extends RuntimeException {

    public DateRangeException() {
    }

    public DateRangeException(String message) {
        super(message);
    }

    public DateRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateRangeException(Throwable cause) {
        super(cause);
    }
    
    
}
