package com.xargsgrep.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

class JsvcStopTask extends DefaultTask {
	final def STOP_FLAG = '-stop'
	final def PIDFILE_FLAG = '-pidfile'

	def settings = project.convention.plugins.jsvc
	def command = []
	
	JsvcStopTask() {
		description = 'Stops jsvc using the configured pidfile'
	}

    @TaskAction
    def run() {
		if (isBlank(settings.pidFile) || settings.pidFile == '/dev/null') {
			throw new TaskExecutionException(this, new Throwable('jsvc pidfile is undefined'))
		}

		command.add(settings.bin)
		command.add(STOP_FLAG)
		addOptionAndValue(PIDFILE_FLAG, settings.pidFile)
		command.add(settings.daemonClass)

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
}
