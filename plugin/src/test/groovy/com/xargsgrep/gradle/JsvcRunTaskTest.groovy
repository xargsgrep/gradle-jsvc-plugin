package com.xargsgrep.gradle

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class JsvcRunTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('jsvc', type: JsvcRunTask)
        assertTrue(task instanceof JsvcRunTask)
    }
}
