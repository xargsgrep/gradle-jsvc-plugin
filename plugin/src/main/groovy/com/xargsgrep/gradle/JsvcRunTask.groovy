package com.xargsgrep.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

class JsvcRunTask extends DefaultTask {
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
	final def KEEPSTDIN_FLAG = '-keepstdin'

	def settings = project.convention.plugins.jsvc
	def command = []
	
	JsvcRunTask() {
		description = 'Runs jsvc with the configured Daemon class'
	}

	@TaskAction
	def run() {
		def requiredProperties = ['bin', 'classpath', 'daemonClass']
		requiredProperties.each {
			if (isBlank(settings[it])) {
				throwException("jsvc ${it} is undefined")
			}
		}

		command.add(settings.bin)
		command.addAll(settings.jvmOptions)
		command.addAll(settings.systemProperties)
		addOptionAndValue(CLASSPATH_FLAG, settings.classpath)
		if (!isBlank(settings.javaHome)) addOptionAndValue(JAVAHOME_FLAG, settings.javaHome)
		if (!settings.detach) command.add(NODETACH_FLAG)
		if (settings.debug) command.add(DEBUG_FLAG)
		if (!isBlank(settings.user)) addOptionAndValue(USER_FLAG, settings.user)
		if (!isBlank(settings.outFile)) addOptionAndValue(OUTFILE_FLAG, settings.outFile)
		if (!isBlank(settings.errorFile)) addOptionAndValue(ERRFILE_FLAG, settings.errorFile)
		if (!isBlank(settings.pidFile)) addOptionAndValue(PIDFILE_FLAG, settings.pidFile)
		if (!isBlank(settings.processName)) addOptionAndValue(PROCNAME_FLAG, settings.processName)
		if (settings.waitTime > 0) addOptionAndValue(WAIT_FLAG, settings.waitTime)
		if (settings.keepStdin) command.add(KEEPSTDIN_FLAG)
		command.add(settings.daemonClass)
		command.addAll(settings.daemonArgs)

		println "jsvc command: " +  command
		command.execute()
	}

	def addOptionAndValue(option, value) {
		command.add(option)
		command.add(value)
	}

	def isBlank(String str) {
		return (str == null || str.length() == 0)
	}

	def throwException(String message) {
		throw new TaskExecutionException(this, new Throwable(message))
	}
}
