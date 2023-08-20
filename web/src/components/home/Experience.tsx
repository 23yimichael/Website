import { experience } from "../../data/experience";
import Link from "../Link";

const Experience = () => {
  return (
    <div className="mt-12">
      <div className="text-2xl font-bold mb-8">Experience</div>
      {experience.map((v, i) => (
        <div key={i} className="flex mb-2">
          <div className="font-extralight opacity-50">{v.date}</div>
          <div className="absolute ml-36">
            {v.position} <Link href={v.href}>@ {v.company}</Link>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Experience;
