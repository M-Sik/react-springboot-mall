import { useEffect, useState } from "react";
import type { ITodo } from "../../types/todo";
import { getOne } from "../../api/todoApi";
import type { AxiosError } from "axios";

interface Props {
  tno: number;
}

export default function Read({ tno }: Props) {
  const [todo, setTodo] = useState<ITodo>();

  useEffect(() => {
    console.log(tno);
    getOne(tno)
      .then((data) => {
        console.log(data);
        setTodo(data);
      })
      .catch((e: AxiosError<{ msg: string }>) =>
        console.error(e.response.data.msg),
      );
  }, [tno]);

  return (
    <>
      {todo && (
        <>
          <div className="border-2 border-sky-200 mt-10 m-2 p-4 text-2xl">
            <MakeDiv title="Tno" value={todo.tno} />
            <MakeDiv title="Writer" value={todo.writer} />
            <MakeDiv title="Title" value={todo.title} />
            <MakeDiv
              title="Complete"
              value={todo.complete ? "Completed" : "Not Yet"}
            />
          </div>
        </>
      )}
    </>
  );
}

const MakeDiv = ({
  title,
  value,
}: {
  title: string;
  value: string | number;
}) => (
  <div className="flex justify-center">
    <div className="relative mb-4 flex w-full flex-wrap items-stretch">
      <div className="w-1/5 p-6 text-right font-bold">{title}</div>
      <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
        {value}
      </div>
    </div>
  </div>
);
