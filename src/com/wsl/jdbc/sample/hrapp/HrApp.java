package com.wsl.jdbc.sample.hrapp;

import com.wsl.jdbc.sample.hrapp.command.*;

import java.text.ParseException;
import java.util.Scanner;

public class HrApp {
    public static void main(String[] args) throws ParseException {
        System.out.println("1-cha_xun_yuan_gong");
        System.out.println("2-yuan_gong_ru_zhi");
        System.out.println("3-update salary");
        System.out.println("4-delete4 salary");
        System.out.println("5-fen_ye_cha_xun");

        Scanner in = new Scanner(System.in);
        Integer cmd = in.nextInt();
        Command command = null;
        switch (cmd) {
            case 1:
                command = new QueryCommand();
                command.execute();
                break;
            case 2:
                command = new InsertCommand();
                command.execute();
                break;
            case 3:
                command = new UpdateCommand();
                command.execute();
                break;
            case 4:
                command = new DelCommand();
                command.execute();
            case 5:
                command = new PageCommand();
                command.execute();
        }
    }
}
//测试Github更新