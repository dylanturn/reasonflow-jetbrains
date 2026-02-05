# ReasonFlow Workflow Schema Documentation

This document describes the JSON schema for ReasonFlow workflow definitions.

## Schema Location

The schema is located at: `src/main/resources/schemas/reasonflow-schema.json`

## Workflow Structure

A ReasonFlow workflow file (`.reasonflow`) is a JSON document that defines an automated workflow with steps, inputs, outputs, and metadata.

### Root Object

| Property | Type | Required | Description |
|----------|------|----------|-------------|
| `name` | string | Yes | The name of the workflow |
| `version` | string | Yes | Semantic version (e.g., "1.0.0") |
| `description` | string | No | Description of what the workflow does |
| `metadata` | object | No | Additional metadata (author, tags, etc.) |
| `inputs` | array | No | Input parameters for the workflow |
| `outputs` | array | No | Output values from the workflow |
| `steps` | array | Yes | The steps that make up the workflow (min 1) |

### Metadata Object

| Property | Type | Description |
|----------|------|-------------|
| `author` | string | The author of the workflow |
| `tags` | array of strings | Tags to categorize the workflow |

### Parameter Object

Used in `inputs` and `outputs` arrays.

| Property | Type | Required | Description |
|----------|------|----------|-------------|
| `name` | string | Yes | The name of the parameter |
| `type` | string | Yes | Data type: "string", "number", "boolean", "object", or "array" |
| `description` | string | No | A description of the parameter |
| `required` | boolean | No | Whether the parameter is required (default: false) |
| `default` | any | No | The default value for the parameter |

### Step Object

Each workflow must have at least one step.

| Property | Type | Required | Description |
|----------|------|----------|-------------|
| `id` | string | Yes | Unique identifier for the step |
| `name` | string | No | Human-readable name for the step |
| `type` | string | Yes | Step type: "action", "decision", "parallel", "loop", or "subworkflow" |
| `description` | string | No | What the step does |
| `action` | string | Conditional | The action to execute (required for "action" and "subworkflow" types) |
| `condition` | string | Conditional | Condition to evaluate (required for "decision" type) |
| `inputs` | object | No | Input values for the step |
| `outputs` | object | No | Output mappings from the step (key-value pairs) |
| `next` | string | No | The ID of the next step to execute |
| `onSuccess` | string | No | Step ID to execute on success (for decision steps) |
| `onFailure` | string | No | Step ID to execute on failure (for decision steps) |
| `branches` | array | No | Branches for decision or parallel steps |
| `retry` | object | No | Retry configuration |
| `timeout` | integer | No | Timeout in milliseconds |

## Step Types

### Action Step

Executes a specific action.

```json
{
  "id": "my_action",
  "type": "action",
  "action": "api.call",
  "inputs": {
    "url": "https://api.example.com",
    "method": "GET"
  },
  "outputs": {
    "response": "result"
  },
  "next": "next_step"
}
```

### Decision Step

Conditional branching based on a condition.

```json
{
  "id": "check_value",
  "type": "decision",
  "condition": "${value} > 10",
  "onSuccess": "success_step",
  "onFailure": "failure_step"
}
```

### Parallel Step

Execute multiple branches concurrently.

```json
{
  "id": "parallel_tasks",
  "type": "parallel",
  "branches": [
    {
      "steps": [
        {
          "id": "task1",
          "type": "action",
          "action": "process.data"
        }
      ]
    },
    {
      "steps": [
        {
          "id": "task2",
          "type": "action",
          "action": "send.notification"
        }
      ]
    }
  ],
  "next": "merge_results"
}
```

### Loop Step

Iterate over a collection.

```json
{
  "id": "process_items",
  "type": "loop",
  "inputs": {
    "items": "${input_array}"
  },
  "branches": [
    {
      "steps": [
        {
          "id": "process_item",
          "type": "action",
          "action": "process.single"
        }
      ]
    }
  ],
  "next": "finalize"
}
```

### Subworkflow Step

Execute another workflow.

```json
{
  "id": "call_subworkflow",
  "type": "subworkflow",
  "action": "shared.workflow",
  "inputs": {
    "param1": "${value}"
  },
  "outputs": {
    "result": "output"
  },
  "next": "final_step"
}
```

## Retry Configuration

| Property | Type | Description |
|----------|------|-------------|
| `maxAttempts` | integer | Maximum number of retry attempts (min: 1) |
| `backoff` | string | Backoff strategy: "fixed", "exponential", or "linear" |
| `delay` | integer | Delay between retries in milliseconds (min: 0) |

Example:

```json
{
  "retry": {
    "maxAttempts": 3,
    "backoff": "exponential",
    "delay": 1000
  }
}
```

## Variable References

You can reference variables using the `${variable}` syntax:

- Input parameters: `${inputName}`
- Step outputs: `${stepId.outputName}`
- Environment variables: `${env.VAR_NAME}`

## Complete Example

See the `examples/` directory for complete workflow examples:

- `simple-workflow.reasonflow` - Basic action steps
- `decision-workflow.reasonflow` - Conditional branching
- `parallel-workflow.reasonflow` - Parallel execution

## Validation

The IDE plugin validates workflows against this schema in real-time, providing:

- Error highlighting for invalid JSON
- Warnings for missing required properties
- Code completion for property names and values
- Quick documentation on hover

## Schema Version

Current schema version: 1.0.0

The schema follows JSON Schema Draft 07 specification.
