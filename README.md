# ReasonFlow JetBrains Plugin

The official ReasonFlow JetBrains plugin for developing ReasonFlow workflows in IntelliJ IDEA, PyCharm, WebStorm, and other JetBrains IDEs.

## Features

- **File Type Support**: Native support for `.reasonflow` workflow files
- **JSON Schema Validation**: Automatic validation of workflow structure against the ReasonFlow schema
- **Syntax Highlighting**: Enhanced JSON syntax highlighting for workflow files
- **Code Completion**: Intelligent code completion for workflow properties and structure
- **Quick Actions**: Create new workflow files directly from the IDE
- **Error Detection**: Real-time error detection and validation

## Installation

### From JetBrains Marketplace (Coming Soon)
1. Open your JetBrains IDE (IntelliJ IDEA, PyCharm, WebStorm, etc.)
2. Go to **Settings/Preferences** → **Plugins** → **Marketplace**
3. Search for "ReasonFlow"
4. Click **Install** and restart the IDE

### From Source
1. Clone this repository
2. Run `./gradlew buildPlugin`
3. In your IDE, go to **Settings/Preferences** → **Plugins** → **⚙️** → **Install Plugin from Disk**
4. Select the generated plugin ZIP from `build/distributions/`
5. Restart the IDE

## Usage

### Creating a New Workflow

1. Right-click on a directory in your project
2. Select **New** → **ReasonFlow Workflow**
3. Enter a name for your workflow
4. A new `.reasonflow` file will be created with a basic template

### Workflow Structure

A ReasonFlow workflow file is a JSON document with the following structure:

```json
{
  "name": "My Workflow",
  "version": "1.0.0",
  "description": "Description of the workflow",
  "metadata": {
    "author": "Your Name",
    "tags": ["example", "workflow"]
  },
  "inputs": [
    {
      "name": "inputParam",
      "type": "string",
      "description": "An input parameter",
      "required": true
    }
  ],
  "outputs": [
    {
      "name": "outputParam",
      "type": "string",
      "description": "An output value"
    }
  ],
  "steps": [
    {
      "id": "step1",
      "name": "First Step",
      "type": "action",
      "action": "some.action",
      "inputs": {
        "param": "${inputParam}"
      },
      "next": "step2"
    },
    {
      "id": "step2",
      "name": "Second Step",
      "type": "decision",
      "condition": "${step1.output} == 'success'",
      "onSuccess": "step3",
      "onFailure": "step4"
    }
  ]
}
```

## Workflow Elements

### Required Fields
- `name`: The name of the workflow
- `version`: Semantic version (e.g., "1.0.0")
- `steps`: Array of workflow steps (at least one required)

### Optional Fields
- `description`: Description of the workflow
- `metadata`: Additional metadata (author, tags, etc.)
- `inputs`: Input parameters for the workflow
- `outputs`: Output values from the workflow

### Step Types

#### Action Step
Executes a specific action:
```json
{
  "id": "action_step",
  "type": "action",
  "action": "action.name",
  "inputs": {},
  "next": "next_step"
}
```

#### Decision Step
Conditional branching based on a condition:
```json
{
  "id": "decision_step",
  "type": "decision",
  "condition": "${value} > 10",
  "onSuccess": "success_step",
  "onFailure": "failure_step"
}
```

#### Parallel Step
Execute multiple branches in parallel:
```json
{
  "id": "parallel_step",
  "type": "parallel",
  "branches": [
    {
      "steps": [...]
    },
    {
      "steps": [...]
    }
  ]
}
```

#### Loop Step
Iterate over a collection:
```json
{
  "id": "loop_step",
  "type": "loop",
  "inputs": {
    "items": "${collection}"
  },
  "branches": [
    {
      "steps": [...]
    }
  ]
}
```

#### Subworkflow Step
Execute another workflow:
```json
{
  "id": "subworkflow_step",
  "type": "subworkflow",
  "action": "workflow.name",
  "inputs": {}
}
```

### Step Features

#### Retry Configuration
```json
{
  "retry": {
    "maxAttempts": 3,
    "backoff": "exponential",
    "delay": 1000
  }
}
```

#### Timeout
```json
{
  "timeout": 30000
}
```

## Development

### Building the Plugin

```bash
./gradlew buildPlugin
```

The plugin will be built in `build/distributions/`.

### Running the Plugin in Development

```bash
./gradlew runIde
```

This will start a new IDE instance with the plugin installed.

### Testing

```bash
./gradlew test
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or contributions, please visit:
https://github.com/dylanturn/reasonflow-jetbrains

## Related Projects

- [ReasonFlow](https://github.com/dylanturn/reason-flow) - The main ReasonFlow project
