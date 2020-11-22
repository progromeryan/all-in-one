import React from 'react';

interface TodoListProps {
  items: { id: string, text: string }[];
  onDeleteTodo: (todoId: string) => void;
}


const TodoList: React.FC<TodoListProps> = props => {
  return (
    <div>
      {props.items.map(item => {
        return <li key={item.id}>
          <span>{item.text}</span>
          <button onClick={() => props.onDeleteTodo(item.id)}>delete</button>
        </li>
      })}
    </div>
  )
}

export default TodoList;
