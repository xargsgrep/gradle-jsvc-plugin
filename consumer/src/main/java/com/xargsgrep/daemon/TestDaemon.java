package com.xargsgrep.daemon;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;

public class TestDaemon implements Daemon {

    @Override
    public void init(DaemonContext daemonContext) throws Exception { }

    @Override
    public void start() throws Exception {
        System.out.println("starting daemon");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stopping daemon");
    }

    @Override
    public void destroy() { }

}
