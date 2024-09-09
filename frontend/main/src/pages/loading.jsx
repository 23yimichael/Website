import Footer from "../components/Footer";
import Spinner from "../components/Spinner";

export default function Loading() {
    return (
        <main>
            <section className="center">
                <Spinner />
            </section>
            <Footer absolute />
        </main>
    );
}
