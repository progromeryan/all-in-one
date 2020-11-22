import React, {useState} from 'react';
import TodoList from "./components/TodoList";
import NewTodo from "./components/NewTodo";
import {Todo} from "./todo.model";

// FC -> Function component
const App: React.FC = () => {
  const [todos, setTodos] = useState<Todo[]>([])

  const todoAddHandler = (text: string) => {
    setTodos(prevState => {
      return [
        ...prevState,
        {
          id: Math.random().toString(),
          text: text
        },
      ]
    });
  }

  const todoDeleteHandler = (todoId: string) => {
    const nextTodos = todos.filter(item => item.id !== todoId);
    setTodos(nextTodos);
  }

  return (
    <div>
      <NewTodo onAddTodo={todoAddHandler}/>
      <TodoList items={todos} onDeleteTodo={todoDeleteHandler}/>
    </div>
  );
}

export default App;
