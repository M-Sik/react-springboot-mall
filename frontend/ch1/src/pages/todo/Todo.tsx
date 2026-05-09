import { Outlet, useNavigate } from "react-router";

export default function Todo() {
  const navigate = useNavigate();

  const handleClickList = () => {
    navigate({ pathname: "/todo/list" });
  };
  const handleClickAdd = () => {
    navigate({ pathname: "/todo/add" });
  };

  return (
    <div>
      <div className="w-full flex m-2 p-2 ">
        <div
          className="text-xl m-1 p-2 w-20 font-extrabold text-center underline cursor-pointer"
          onClick={handleClickList}
        >
          LIST
        </div>

        <div
          className="text-xl m-1 p-2 w-20 font-extrabold text-center underline cursor-pointer"
          onClick={handleClickAdd}
        >
          ADD
        </div>
      </div>
      <div className="flex flex-wrap w-full">
        <Outlet />
      </div>
    </div>
  );
}
