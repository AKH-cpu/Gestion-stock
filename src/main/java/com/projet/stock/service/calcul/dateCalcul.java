/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.calcul;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author anoir
 */
public class dateCalcul {

    public static Date currentTime() {
        Date d = new Date();
        return d;
    }

    public static long getDifferenceHours(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date d1, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date d2) {
        long diff = (d1.getTime() - d2.getTime());
        // gettime() : the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this date.
        // long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        // long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffHours;
    }
}
