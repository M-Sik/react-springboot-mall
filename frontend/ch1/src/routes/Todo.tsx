import { lazy, Suspense } from "react";
import { Navigate } from "react-router";

const Loading = () => <div>Loading...</div>;
const Todo = lazy(() => import("../pages/todo/Todo"));
const TodoList = lazy(() => import("../pages/todo/TodoList"));
const TodoRead = lazy(() => import("../pages/todo/TodoRead"));
const TodoAdd = lazy(() => import("../pages/todo/TodoAdd"));
const TodoModify = lazy(() => import("../pages/todo/TodoModify"));

export const TodoRouter = () => {
  return {
    path: "/todo",
    Component: Todo,
    children: [
      {
        path: "",
        element: <Navigate to={"/todo/list"}></Navigate>,
      },
      {
        path: "list",
        element: (
          <Suspense fallback={<Loading />}>
            <TodoList />
          </Suspense>
        ),
      },
      {
        path: "read/:tno",
        element: (
          <Suspense fallback={<Loading />}>
            <TodoRead />
          </Suspense>
        ),
      },
      {
        path: "add",
        element: (
          <Suspense fallback={<Loading />}>
            <TodoAdd />
          </Suspense>
        ),
      },
      {
        path: "modify/:tno",
        element: (
          <Suspense fallback={<Loading />}>
            <TodoModify />
          </Suspense>
        ),
      },
    ],
  };
};
