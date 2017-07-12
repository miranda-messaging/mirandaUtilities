package com.ltsllc.miranda.utilities;

/**
 * Created by clarkhobbie on 7/8/17.
 */
public interface Command {
    public void go () throws CommandException;
    public void check () throws CommandException;
}
