package com.xargsgrep.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.file.FileCollection

class JsvcPlugin implements Plugin<Project> {
	void apply(Project project) {
		def javaConvention = project.convention.plugins.java
		def classpath = (javaConvention == null) ? null : javaConvention.sourceSets.main.runtimeClasspath
		project.convention.plugins.jsvc = new JsvcPluginConvention(classpath)

		project.task('jsvcStart', type: JsvcStartTask)
		project.task('jsvcStop', type: JsvcStopTask)
	}
}

class JsvcPluginConvention {
	String bin = '/usr/bin/jsvc'
	boolean debug = false
	boolean detach = true
	boolean keepStdin = false
	String outFile
	String errorFile
	String pidFile
	int waitTime = 0
	String javaHome
	String processName 
	String user
	String daemonClass
	List<String> daemonArgs = []
	List<String> systemProperties = []
	List<String> jvmOptions = []
	FileCollection classpath

	JsvcPluginConvention(classpath) {
		this.classpath = classpath
	}

	def jsvc(Closure closure) {
		closure.delegate = this
		closure()
	}
}
