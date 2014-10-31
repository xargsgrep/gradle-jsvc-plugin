gradle-jsvc-plugin
==================

Custom Gradle plugin to facilitate running projects which use jsvc. Automatically applies the java plugin.

# Tasks
* jsvcStart Starts jsvc with the configured daemon class (depends on java plugin's classes task)
* jsvcStop Stops jsvc using the configured pidfile

# Usage
```groovy
apply plugin: 'jsvc'

jsvc {
	// required
	daemonClass = 'com.xargsgrep.daemon.TestDaemon'

	// optional
	bin = '/sbin/jsvc' // defaults to /usr/bin/jsvc
	classpath = fileTree(buildDir) // defaults to java runtimeClasspath
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
	jvm = ['client' 'jvm' 'hotspot' 'server']
	processName = 'Jsvc Process'
	user = 'username' // default to jsvc default (current user)
	waitTime = 20 // must be multiple of 10, per jsvc
}
```
