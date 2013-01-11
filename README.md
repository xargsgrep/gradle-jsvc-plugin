gradle-jsvc-plugin
==================

Custom Gradle plugin to facilitate running projects which use jsvc

# Tasks
* jsvcStart Starts jsvc with the configured daemon class
* jsvcStop Stops jsvc using the configured pidfile

# Usage
```groovy
apply plugin: 'jsvc'

jsvc {
	// required
	daemonClass = 'com.xargsgrep.daemon.TestDaemon'

	// optional if java plugin is applied, otherwise required
	// this is of type FileCollection
	classpath = fileTree(buildDir) // defaults to java runtimeClasspath if java plugin is applied

	// optional
	bin = '/sbin/jsvc' // defaults to /usr/bin/jsvc
	systemProperties = ['-DtestProperty1=foo', '-DtestProperty2=bar']
	jvmOptions = ['-Xms64m', '-Xmx64m']
	javaHome = '/usr/lib/jvm' // defaults to jsvc default ($JAVA_HOME)
	debug = true // defaults to false
	detach = false // defaults to true
	keepStdin = true // defaults to false
	outFile = 'jsvc.log' // defaults to jsvc default (/dev/null)
	errorFile = '&1' // defaults to jsvc default (/dev/null)
	pidFile = 'jsvc.pid' // defaults to jsvc default (/var/run/jsvc.pid)
	daemonArgs = ['arg1', 'arg2']
	processName = 'Jsvc Process'
	user = 'username' // default to jsvc default (current user)
	waitTime = 20 // must be multiple of 10, per jsvc
}
```
