package com.xargsgrep.gradle

class JsvcStartTask extends AbstractJsvcTask {

    JsvcStartTask() {
        description = 'Starts jsvc with the configured daemon class'
    }

    def getRequiredProperties() {
        return ['bin', 'classpath', 'daemonClass']
    }

    def addArguments() {
        arguments.addAll(settings.jvmOptions)
        arguments.addAll(settings.systemProperties)
        arguments.addAll(CLASSPATH_FLAG, settings.classpath.getAsPath())
        if (!isBlank(settings.javaHome)) arguments.addAll(JAVAHOME_FLAG, settings.javaHome)
        if (!settings.detach) arguments.add(NODETACH_FLAG)
        if (settings.debug) arguments.add(DEBUG_FLAG)
        if (!isBlank(settings.user)) arguments.addAll(USER_FLAG, settings.user)
        if (!isBlank(settings.outFile)) arguments.addAll(OUTFILE_FLAG, settings.outFile)
        if (!isBlank(settings.errorFile)) arguments.addAll(ERRFILE_FLAG, settings.errorFile)
        if (!isBlank(settings.pidFile)) arguments.addAll(PIDFILE_FLAG, settings.pidFile)
        if (!isBlank(settings.jvm)) arguments.addAll(JVM_FLAG, setting.jvm)
        if (!isBlank(settings.processName)) arguments.addAll(PROCNAME_FLAG, settings.processName)
        if (settings.waitTime > 0) arguments.addAll(WAIT_FLAG, settings.waitTime)
        if (settings.keepStdin) arguments.add(KEEPSTDIN_FLAG)
    }

}
