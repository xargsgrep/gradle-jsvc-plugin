package com.xargsgrep.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

class JsvcStopTask extends AbstractJsvcTask {

	JsvcStopTask() {
		description = 'Stops jsvc using the configured pidfile'
	}

	def getRequiredProperties() {
		return ['pidFile']
	}

	def buildCommand() {
		command.add(STOP_FLAG)
		addOptionAndValue(PIDFILE_FLAG, settings.pidFile)
	}

}
