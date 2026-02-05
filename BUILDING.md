# Building and Development

## Prerequisites

- JDK 17 or later
- Gradle 8.2.1 (will be downloaded by gradlew)
- IntelliJ IDEA (recommended for development)

## Building the Plugin

Due to network restrictions, the JetBrains IntelliJ SDK may not be downloadable in all environments. If you encounter network issues:

1. Build locally on your machine with network access:
   ```bash
   ./gradlew buildPlugin
   ```

2. The plugin will be built as a ZIP file in `build/distributions/`

## Installing the Plugin

1. Open IntelliJ IDEA or another JetBrains IDE
2. Go to **Settings/Preferences** → **Plugins** → **⚙️** → **Install Plugin from Disk**
3. Select the ZIP file from `build/distributions/`
4. Restart the IDE

## Development

### Running in Development Mode

```bash
./gradlew runIde
```

This starts a new IDE instance with the plugin installed for testing.

### Project Structure

```
reasonflow-jetbrains/
├── src/main/
│   ├── kotlin/com/reasonflow/          # Kotlin source files
│   │   ├── ReasonFlowFileType.kt      # File type definition
│   │   ├── ReasonFlowLanguage.kt      # Language definition
│   │   ├── ReasonFlowSchemaProviderFactory.kt  # JSON schema provider
│   │   └── actions/
│   │       └── NewWorkflowAction.kt    # New file action
│   └── resources/
│       ├── META-INF/
│       │   └── plugin.xml              # Plugin configuration
│       ├── schemas/
│       │   └── reasonflow-schema.json  # Workflow JSON schema
│       ├── fileTemplates/
│       │   └── ReasonFlow Workflow.reasonflow.ft  # File template
│       └── icons/
│           └── reasonflow.svg          # Plugin icon
├── examples/                            # Example workflow files
│   ├── simple-workflow.reasonflow
│   ├── decision-workflow.reasonflow
│   └── parallel-workflow.reasonflow
├── build.gradle.kts                     # Gradle build configuration
├── settings.gradle.kts                  # Gradle settings
├── gradle.properties                    # Gradle properties
└── README.md                            # Main documentation

```

## Testing

### Manual Testing

1. Run `./gradlew runIde` to start a test IDE instance
2. Create a new project or open an existing one
3. Right-click on a directory
4. Select **New** → **ReasonFlow Workflow**
5. Enter a name and test the file features:
   - Syntax highlighting
   - Code completion (Ctrl+Space)
   - Validation errors for invalid JSON
   - Schema validation

### Automated Testing

```bash
./gradlew test
```

## Troubleshooting

### Network Issues

If you see errors like "cache-redirector.jetbrains.com", this means the build system cannot download the IntelliJ SDK. This is a common issue in restricted network environments. 

**Solution:** Build on a machine with full internet access and share the built ZIP file.

### Gradle Version Issues

If you encounter Gradle compatibility issues:
1. Check `gradle/wrapper/gradle-wrapper.properties` for the correct Gradle version (8.2.1)
2. Run `./gradlew wrapper --gradle-version=8.2.1` to regenerate wrapper files

### Plugin Not Loading

1. Check that plugin.xml is correctly formatted
2. Verify all required classes are present in src/main/kotlin
3. Check IDE logs: **Help** → **Show Log in Explorer/Finder**
