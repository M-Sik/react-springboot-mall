import { NavLink } from "react-router";

export default function About() {
  return (
    <section className="text-3xl">
      <div className="flex">
        <NavLink to="/">Main</NavLink>
      </div>
      <p>About page</p>
    </section>
  );
}
