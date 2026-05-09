import { useSearchParams } from "react-router";

export default function TodoList() {
  const [queryParmas] = useSearchParams();

  const page = queryParmas.get("page");
  const size = queryParmas.get("size");

  return (
    <div className="bg-white w-full">
      <div className="text-4xl">
        Todo List Page {page} {size}
      </div>
    </div>
  );
}
