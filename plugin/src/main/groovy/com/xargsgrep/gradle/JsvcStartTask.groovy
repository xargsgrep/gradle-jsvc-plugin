package com.xargsgrep.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException

class JsvcStartTask extends AbstractJsvcTask {

	JsvcStartTask() {
		description = 'Starts jsvc with the configured daemon class'
	}

	def getRequiredProperties() {
		return ['bin', 'classpath', 'daemonClass']
	}

	def buildCommand() {
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
	}

}
