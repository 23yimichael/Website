"use client";

import Image from "next/image";
import Link from "next/link";
import Hoverable from "./Hoverable";

const hero = [
  {
    name: "About",
    href: "/about",
  },
  {
    name: "Portfolio",
    href: "/portfolio",
  },
  {
    name: "Blog",
    href: "/blog",
  },
  {
    name: "Resume",
    href: `${process.env.NEXT_PUBLIC_WEB_URL}/Resume.pdf`,
  },
];

export default function Hero() {
  return (
    <div
      className="absolute
             left-1/2
             top-1/2
             -translate-x-1/2
             -translate-y-1/2
             transform
             flex
             flex-col
             items-center
             text-center"
    >
      <Image
        alt="michael"
        className="rounded-full"
        height={100}
        src="/michael.png"
        width={100}
      />
      <div className="mt-4 text-2xl font-medium">Michael Yi</div>
      <div className="mt-1 text-xs font-light opacity-50">
        Software Engineer
      </div>
      <div className="flex mt-2 text-[10px]">
        {hero.map((v, i) => {
          return (
            <Hoverable key={v.name}>
              {i !== 0 && <>&nbsp;&nbsp;&#183;&nbsp;&nbsp;</>}
              <Link href={v.href}>{v.name}</Link>
            </Hoverable>
          );
        })}
      </div>
    </div>
  );
}
