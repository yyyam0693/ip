# Bot With A Plan

Bot With A Plan is a lightweight task manager chatbot built in Java.  
It allows users to manage todos, deadlines, and events through simple text commands.

---

## Getting Started

Type a command into the input box and press **Send**.

All commands are case-sensitive and must follow the exact formats shown below.

---

## Features

### 1. Add a Todo

Adds a task without a date.

**Format**
```text
todo <description>
```

**Example**
```text
todo read book
```

---

### 2. Add a Deadline

Adds a task with a due date.

**Format**
```text
deadline <description> /by <yyyy-mm-dd>
```

**Example**
```text
deadline submit report /by 2026-03-01
```

---

### 3. Add an Event

Adds a task with a start and end time.

**Format**
```text
event <description> /from <start> /to <end>
```

**Example**
```text
event project meeting /from 2pm /to 4pm
```

---

### 4. List Tasks

Displays all tasks currently stored.

**Format**
```text
list
```

---

### 5. Mark a Task as Done

Marks a task as completed.

**Format**
```text
mark <task number>
```

**Example**
```text
mark 2
```

---

### 6. Unmark a Task

Marks a task as not completed.

**Format**
```text
unmark <task number>
```

**Example**
```text
unmark 2
```

---

### 7. Delete a Task

Removes a task from the list.

**Format**
```text
delete <task number>
```

**Example**
```text
delete 3
```

---

### 8. Find Tasks

Finds tasks containing a keyword.

**Format**
```text
find <keyword>
```

**Example**
```text
find book
```

---

### 9. Duplicate Detection

The application prevents duplicate tasks.

Two tasks are considered duplicates if they:
- Have the same task type (Todo / Deadline / Event)
- Have the same description (case-insensitive)

If a duplicate task is added, the bot will reject it.

---

### 10. Exit the Application

Closes the application.

**Format**
```text
bye
```

---

## Data Storage

All tasks are automatically saved to:

```text
data/duke.txt
```

Tasks will be restored when the application is reopened.

---

## Command Summary

| Action | Command |
|--------|----------|
| Add Todo | `todo <description>` |
| Add Deadline | `deadline <description> /by <yyyy-mm-dd>` |
| Add Event | `event <description> /from <start> /to <end>` |
| List | `list` |
| Mark | `mark <task number>` |
| Unmark | `unmark <task number>` |
| Delete | `delete <task number>` |
| Find | `find <keyword>` |
| Exit | `bye` |
