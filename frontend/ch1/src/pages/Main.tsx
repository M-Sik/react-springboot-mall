import { NavLink } from "react-router";

export default function Main() {
  return (
    <section className="text-3xl">
      <div className="flex">
        <NavLink to="/about">About</NavLink>
      </div>
      <p>Main page</p>
    </section>
  );
}
