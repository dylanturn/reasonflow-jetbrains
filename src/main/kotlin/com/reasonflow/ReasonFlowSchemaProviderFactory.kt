package com.reasonflow

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider
import com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory
import com.jetbrains.jsonSchema.extension.SchemaType

class ReasonFlowSchemaProviderFactory : JsonSchemaProviderFactory {
    override fun getProviders(project: Project): List<JsonSchemaFileProvider> {
        return listOf(ReasonFlowSchemaProvider(project))
    }
}

class ReasonFlowSchemaProvider(private val project: Project) : JsonSchemaFileProvider {
    override fun isAvailable(file: VirtualFile): Boolean {
        return file.extension == "reasonflow"
    }

    override fun getName(): String = "ReasonFlow Workflow Schema"

    override fun getSchemaFile(): VirtualFile? {
        return JsonSchemaProviderFactory.getResourceFile(javaClass, "/schemas/reasonflow-schema.json")
    }

    override fun getSchemaType(): SchemaType = SchemaType.embeddedSchema
}
