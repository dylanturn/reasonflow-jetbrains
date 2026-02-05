package com.reasonflow.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class NewWorkflowAction : CreateFileFromTemplateAction(
    "ReasonFlow Workflow",
    "Create a new ReasonFlow workflow file",
    IconLoader.getIcon("/icons/reasonflow.svg", NewWorkflowAction::class.java)
) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("New ReasonFlow Workflow")
            .addKind("Basic Workflow", IconLoader.getIcon("/icons/reasonflow.svg", javaClass), "ReasonFlow Workflow")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String {
        return "Create ReasonFlow Workflow $newName"
    }
}
