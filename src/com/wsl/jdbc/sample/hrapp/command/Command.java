package com.wsl.jdbc.sample.hrapp.command;

import java.text.ParseException;

public interface Command {
    default void execute() throws ParseException {
    }
}
