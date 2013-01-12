package com.xargsgrep.gradle

class JsvcStopTask extends AbstractJsvcTask {

    JsvcStopTask() {
        description = 'Stops jsvc using the configured pidfile'
    }

    def getRequiredProperties() {
        return ['pidFile']
    }

    def addArguments() {
        arguments.add(STOP_FLAG)
        arguments.addAll(PIDFILE_FLAG, settings.pidFile)
    }

}
