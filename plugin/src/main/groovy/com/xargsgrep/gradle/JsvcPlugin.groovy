package com.xargsgrep.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileCollection

class JsvcPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create('jsvc', JsvcPluginExtension)
        project.task('jsvcStart', type: JsvcStartTask)
        project.task('jsvcStop', type: JsvcStopTask)
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
    String processName
    String user
    String workingDir
    int waitTime = 0
    List<String> daemonArgs = []
    List<String> systemProperties = []
    List<String> jvmOptions = []
    FileCollection classpath

}
