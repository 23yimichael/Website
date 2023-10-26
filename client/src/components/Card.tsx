"use client";

import Image from "next/image";
import { FC } from "react";
import { FiArrowUpRight } from "react-icons/fi";

interface Props {
   name: string;
   date: string;
   desc?: string;
   href?: string;
   tech?: string;
   img?: string;
}

const Card: FC<Props> = ({ name, date, desc, href, tech, img }) => {
   return (
      <div className="flex sm:flex-col md:flex-row">
         <div className="font-light text-[13px] text-neutral-400 w-48 sm:mb-2 md:mb-0">
            {date}
         </div>
         <div className="sm:w-72 md:w-96">
            <a
               rel="noopener noreferrer"
               target="_blank"
               href={href && href}
               className={`flex gap-1 font-normal text-sm ${
                  href && "cursor-pointer duration-500 hover:opacity-50"
               }`}
            >
               {name}
               {href && <FiArrowUpRight size={12} />}
            </a>
            {desc && (
               <div className="text-xs mt-2 text-neutral-500">{desc}</div>
            )}
            {tech && (
               <div className="mt-2 text-[10px] text-neutral-400">{tech}</div>
            )}
            {img && (
               <Image
                  className="mt-4 
                                 border-neutral-300 
                                 border-[1px] 
                                 rounded-lg 
                                 shadow-md"
                  src={img}
                  alt={name}
                  width={200}
                  height={120}
               />
            )}
         </div>
      </div>
   );
};

export default Card;
