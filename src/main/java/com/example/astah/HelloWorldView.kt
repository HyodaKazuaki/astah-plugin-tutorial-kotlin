package com.example.astah

import com.change_vision.jude.api.inf.AstahAPI
import com.change_vision.jude.api.inf.project.ProjectEvent
import com.change_vision.jude.api.inf.project.ProjectEventListener
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView
import com.change_vision.jude.api.inf.ui.ISelectionListener
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane

class HelloWorldView: JPanel(), IPluginExtraTabView, ProjectEventListener {
    init {
        initComponents()
    }

    private fun initComponents() {
        add(createLabelPane(), BorderLayout.CENTER)
        addProjectEventListener()
    }
    private fun addProjectEventListener() {
        try {
            val api = AstahAPI.getAstahAPI()
            val projectAccessor = api.projectAccessor
            projectAccessor.addProjectEventListener(this)
        } catch (e: ClassNotFoundException) {
            e.message
        }
    }

    private fun createLabelPane(): Container {
        val label = JLabel("Hello World")
        val pane = JScrollPane(label)
        return pane
    }

    override fun projectChanged(e: ProjectEvent?) {}
    override fun projectClosed(e: ProjectEvent?) {}
    override fun projectOpened(e: ProjectEvent?) {}
    override fun addSelectionListener(listener: ISelectionListener?) {}

    override fun getComponent(): Component {
        return this
    }

    override fun getDescription(): String {
        return "Show \"Hello World\" here."
    }

    override fun getTitle(): String {
        return "Hello World View"
    }

    override fun activated() {}
    override fun deactivated() {}
}