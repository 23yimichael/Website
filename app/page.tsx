import About from "@/app/components/About";
import Experience from "@/app/components/Experience";
import Footer from "@/app/components/Footer";
import Projects from "@/app/components/Projects";

const Home = () => {
  return (
    <div>
      <div className="flex flex-col min-h-screen w-full items-center justify-center">
        <div className="w-[768px]">
          <About />
          <br />
          <br />
          <Experience />
          <br />
          <br />
          <Projects />
          <br />
          <br />
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Home;
