(ns marsrover.core)

(defn- forward
  ([rover]
   (case (:d rover)
     :N (forward rover :y +)
     :E (forward rover :x +)
     :S (forward rover :y -)
     :W (forward rover :x -)))

  ([rover coord op]
   (assoc rover coord (op (rover coord) 1))))

(defn- turn-right [rover]
  (case (:d rover)
    :N (assoc rover :d :E)))

(defn- a-move [rover instr]
  (case instr
    \M (forward rover)
    \R (turn-right rover)))

(defn move [rover instructions]
  (reduce a-move rover (seq instructions)))
