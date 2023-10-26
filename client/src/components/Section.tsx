"use client";

import Link from "next/link";
import { FC, ReactNode } from "react";
import { FiArrowUpRight } from "react-icons/fi";

interface Props {
   title: string;
   children: ReactNode;
   href?: string;
   nonDesc?: boolean;
}

const Section: FC<Props> = ({ title, children, href, nonDesc = false }) => {
   return (
      <div className="mt-10">
         {href ? (
            <Link
               href={href}
               className="flex
                             gap-1
                             font-normal
                             cursor-pointer
                             duration-500
                             hover:opacity-50"
            >
               {title}
               {<FiArrowUpRight size={12} />}
            </Link>
         ) : (
            <div className="font-normal">{title}</div>
         )}
         <div className={`mt-6 flex flex-col ${nonDesc ? "gap-4" : "gap-8"}`}>
            {children}
         </div>
      </div>
   );
};

export default Section;
