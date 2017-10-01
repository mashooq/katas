(ns marsrover.core)

(defn- forward [rover]
  (case (:d rover)
    :N (assoc rover :y  (+ (:y rover) 1))))

(defn- a-move [rover instr]
  (case instr
    \M (forward rover)))

(defn move [rover instructions]
  (reduce a-move rover (seq instructions)))
