package com.xargsgrep.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

abstract class AbstractJsvcTask extends DefaultTask {

    final def CLASSPATH_FLAG = '-classpath'
    final def JAVAHOME_FLAG = '-java-home'
    final def NODETACH_FLAG = '-nodetach'
    final def DEBUG_FLAG = '-debug'
    final def USER_FLAG = '-user'
    final def OUTFILE_FLAG = '-outfile'
    final def ERRFILE_FLAG = '-errfile'
    final def PIDFILE_FLAG = '-pidfile'
    final def PROCNAME_FLAG = '-procname'
    final def WAIT_FLAG = '-wait'
    final def STOP_FLAG = '-stop'
    final def KEEPSTDIN_FLAG = '-keepstdin'

    def arguments = []
    def settings

    abstract getRequiredProperties()
    abstract addArguments()

    AbstractJsvcTask() {
        settings = project.extensions.getByName('jsvc')
    }

    @TaskAction
    def run() {
        if (settings.classpath == null) {
            def javaConvention = project.convention.plugins.java
            settings.classpath = javaConvention.sourceSets.main.runtimeClasspath
        }

        def requiredProperties = getRequiredProperties()
        requiredProperties.each {
            if (isBlank(settings[it])) {
                throwException("jsvc ${it} is undefined")
            }
        }

        addArguments()
        arguments.add(settings.daemonClass)
        arguments.addAll(settings.daemonArgs)

        project.exec({
            executable = settings.bin
            args arguments
        })
    }

    def isBlank(String str) {
        return (str == null || str.length() == 0)
    }

    def isBlank(FileCollection collection) {
        return (collection == null || collection.isEmpty())
    }

    def throwException(String message) {
        throw new TaskExecutionException(this, new Throwable(message))
    }

}
