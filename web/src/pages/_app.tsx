import type { AppProps } from "next/app";
import Providers from "../providers";
import "../styles/globals.css";

export const metadata = {
  title: "Michael Yi",
  description: "© 2023 Michael Yi, All Rights Reserved.",
  icons: {
    icon: "/Michael.png",
  },
};

export default function App({ Component, pageProps }: AppProps) {
  return (
    <Providers>
      <Component {...pageProps} />
    </Providers>
  );
}
