import { useParams } from "react-router";

export default function TodoModify() {
  const { tno } = useParams();

  console.log(tno);

  return (
    <div className="bg-white w-full">
      <div className="text-4xl">TodoModify {tno}</div>
    </div>
  );
}
