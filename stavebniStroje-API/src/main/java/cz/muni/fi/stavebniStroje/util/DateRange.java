/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.stavebniStroje.util;

import java.util.Date;

/**
 *
 * @author spito
 */
public class DateRange {
    private final Date begin;
    private final Date end;

    public DateRange(Date begin, Date end) {
        if (begin.after(end)) {
            throw new IllegalArgumentException("End date cannot be before begin date.");
        }
        this.begin = begin;
        this.end = end;
    }
    
    public boolean inRange(Date date) {
        return !(date.before(begin) || date.after(end));        
    }
    
    public boolean before(Date date) {
        return date.before(begin);
    }
    
    public boolean after(Date date) {
        return date.after(end);
    }
    
    public boolean interleave(DateRange range) {
        return !(range.before(end) || range.after(begin));
    }
}
