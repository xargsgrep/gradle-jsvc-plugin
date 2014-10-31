package com.xargsgrep.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.plugins.JavaPlugin

class JsvcPlugin implements Plugin<Project> {

    public static final JSVC_START = 'jsvcStart'
    public static final JSVC_STOP = 'jsvcStop'

    void apply(Project project) {
        project.plugins.apply(JavaPlugin.class)
        project.extensions.create('jsvc', JsvcPluginExtension)
        project.task(JSVC_START, type: JsvcStartTask).dependsOn(JavaPlugin.CLASSES_TASK_NAME)
        project.task(JSVC_STOP, type: JsvcStopTask)
    }

}

class JsvcPluginExtension {

    String bin = '/usr/bin/jsvc'
    String daemonClass
    boolean debug = false
    boolean detach = true
    boolean keepStdin = false
    String outFile = '&1'
    String errorFile = '&1'
    String pidFile
    String javaHome
    String jvm
    String processName
    String user
    String workingDir
    int waitTime = 0
    List<String> daemonArgs = []
    List<String> systemProperties = []
    List<String> jvmOptions = []
    FileCollection classpath

}
