import { useParams } from "react-router";

import Read from "../../components/todo/Read";

export default function TodoRead() {
  const { tno } = useParams();

  console.log(tno);

  return (
    <div className="bg-white w-full">
      <div className="text-4xl">TodoRead{tno}</div>
      <Read tno={Number(tno)} />
    </div>
  );
}
