package com.xargsgrep.gradle

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class JsvcPluginTest {
    @Test
    public void greeterPluginAddsJsvcRunTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'jsvc'

        assertTrue(project.tasks.jsvcRun instanceof JsvcRunTask)
    }
}
