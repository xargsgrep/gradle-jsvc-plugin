package com.xargsgrep.gradle

import org.gradle.api.DefaultTask
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

	def settings = project.convention.plugins.jsvc
	def command = []

	abstract getRequiredProperties()
	abstract buildCommand()

	@TaskAction
	def run() {
		def requiredProperties = getRequiredProperties()
		requiredProperties.each {
			if (isBlank(settings[it])) {
				throwException("jsvc ${it} is undefined")
			}
		}

		command.add(settings.bin)
		buildCommand()
		command.add(settings.daemonClass)
		command.addAll(settings.daemonArgs)

		println "jsvc command: " + command
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
