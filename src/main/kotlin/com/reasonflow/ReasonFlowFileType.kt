package com.reasonflow

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object ReasonFlowFileType : LanguageFileType(ReasonFlowLanguage) {
    override fun getName(): String = "ReasonFlow"
    
    override fun getDescription(): String = "ReasonFlow workflow file"
    
    override fun getDefaultExtension(): String = "reasonflow"
    
    override fun getIcon(): Icon = IconLoader.getIcon("/icons/reasonflow.svg", javaClass)
}
