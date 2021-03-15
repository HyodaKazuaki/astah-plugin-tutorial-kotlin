package com.example.astah

import com.change_vision.jude.api.inf.ui.IPluginActionDelegate
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate.UnExpectedException
import com.change_vision.jude.api.inf.ui.IWindow
import java.lang.Exception
import javax.swing.JOptionPane
import kotlin.jvm.Throws

class HelloWorldAction: IPluginActionDelegate {
    @Throws(UnExpectedException::class)
    override fun run(window: IWindow) {
        try {
            JOptionPane.showMessageDialog(window.parent, "Hello World!!")
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(window.parent, "Exception occurred", "Alert", JOptionPane.ERROR_MESSAGE)
            throw UnExpectedException()
        }
    }
}