import { useRef } from "react";
import { useInView } from "framer-motion";

// eslint-disable-next-line react/prop-types
function Section({ children }) {
  const ref = useRef(null);
  const isInView = useInView(ref, { once: true });
  return (
    <section ref={ref}>
      <div
        className="cartas"
        style={{
          transform: isInView ? "none" : "translateX(-200px)",
          opacity: isInView ? 1 : 0,
          transition: "all 0.9s cubic-bezier(0.17, 0.55, 0.55, 1) 0.3s",
        }}>
        {children}
      </div>
    </section>
  );
}
export default Section;
