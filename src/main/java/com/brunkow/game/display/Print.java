package com.brunkow.game.display;

import org.apache.commons.lang3.StringUtils;

public class Print {
    public static String print(int yardline, char team){
        StringBuffer buff = new StringBuffer();
        for (int i = 1; i<=100; i++) {
            if (i == yardline)
                buff.append(team);
            else
                buff.append(".");
        }
        return buff.toString();
    }
    public static String print(double yardline, char team){
        return print((int)yardline, team);
    }
}
