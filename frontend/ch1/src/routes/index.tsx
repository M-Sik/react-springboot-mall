import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router";

import BasicLayout from "../layouts/BasicLayout";
import { TodoRouter } from "./Todo";

const Loading = () => <div>Loading...</div>;

const Main = lazy(() => import("../pages/Main"));
const About = lazy(() => import("../pages/About"));

export const router = createBrowserRouter([
  {
    Component: BasicLayout,
    children: [
      {
        path: "/",
        element: (
          <Suspense fallback={<Loading />}>
            <Main />
          </Suspense>
        ),
      },
      {
        path: "/about",
        element: (
          <Suspense fallback={<Loading />}>
            <About />
          </Suspense>
        ),
      },
      TodoRouter(),
    ],
  },
]);
