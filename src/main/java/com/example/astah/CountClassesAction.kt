package com.example.astah

import com.change_vision.jude.api.inf.AstahAPI
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException
import com.change_vision.jude.api.inf.model.IClass
import com.change_vision.jude.api.inf.model.INamedElement
import com.change_vision.jude.api.inf.model.IPackage
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate.UnExpectedException
import com.change_vision.jude.api.inf.ui.IWindow
import java.lang.Exception
import javax.swing.JOptionPane
import kotlin.jvm.Throws

class CountClassesAction: IPluginActionDelegate {
    @Throws(UnExpectedException::class)
    override fun run(window: IWindow) {
        try {
            val api = AstahAPI.getAstahAPI()
            val projectAccessor = api.projectAccessor
            val ICurrentModel = projectAccessor.project
            var classesList = ArrayList<IClass>()
            getAllClasses(ICurrentModel, classesList)
            JOptionPane.showMessageDialog(window.parent, "There are " + classesList.size + " classes.")
        } catch (e: ProjectNotFoundException) {
            val message = "Please open a project."
            JOptionPane.showMessageDialog(window.parent, message, "Warning", JOptionPane.WARNING_MESSAGE)
            throw CalculateUnExpectedException()
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(window.parent, "Exception occurred", "Alert", JOptionPane.ERROR_MESSAGE)
            throw UnExpectedException()
        }
    }

    public class CalculateUnExpectedException: UnExpectedException() {
    }

    @Throws(ClassNotFoundException::class, ProjectNotFoundException::class)
    private fun getAllClasses(element: INamedElement, classList: MutableList<IClass>) {
        if (element is IPackage) {
            for (ownedNamedElement in element.ownedElements) {
                getAllClasses(ownedNamedElement, classList)
            }
        } else if (element is IClass) {
            classList.add(element)
            for (nestedClasses in element.nestedClasses) {
                getAllClasses(nestedClasses, classList)
            }
        }
    }
}