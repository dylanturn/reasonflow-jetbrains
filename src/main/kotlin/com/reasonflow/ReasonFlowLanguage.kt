package com.reasonflow

import com.intellij.lang.Language

object ReasonFlowLanguage : Language("ReasonFlow", "application/json") {
    override fun getDisplayName(): String = "ReasonFlow"
}
