(ns marsrover.core)

(defn- wrap-if-needed
  ([grid axis rover]
   (let [current (rover axis) max (- (grid axis) 1)]
     (cond
       (> current max) (assoc rover axis 0)
       (< current 0) (assoc rover axis max)
       :else rover)))
  ([grid rover]
   (->> rover
        (wrap-if-needed grid :x)
        (wrap-if-needed grid :y))))

(defn- check-obstacle [current-position new-position obstacle]
  (if (= (dissoc new-position :d) obstacle)
    current-position
    new-position))

(defn- check-obstacles [obstacles current-position new-position]
  (reduce (partial check-obstacle current-position) new-position (seq obstacles)))

(def moves
  {:N {:coord :y :op +}
   :E {:coord :x :op +}
   :S {:coord :y :op -}
   :W {:coord :x :op -}})

(defn- forward
  ([grid rover]
   (let [move (moves (:d rover))]
     (forward grid rover (:coord move) (:op move))))

  ([grid rover coord op]
   (->> (assoc rover coord (op (rover coord) 1))
        (check-obstacles (:obstacles grid) rover)
        (wrap-if-needed grid))))

(def turns
  {:N {:right :E :left :W}
   :E {:right :S :left :N}
   :S {:right :W :left :E}
   :W {:right :N :left :S}})

(defn- turn-right [rover]
  (let [turn (turns (:d rover))]
    (assoc rover :d (:right turn))))

(defn- turn-left [rover]
  (let [turn (turns (:d rover))]
    (assoc rover :d (:left turn))))

(defn- a-move [grid rover instr]
  (case instr
    \M (forward grid rover)
    \R (turn-right rover)
    \L (turn-left rover)))

(defn move [grid rover instructions]
  (let [a-move-in-grid (partial a-move grid)]
    (reduce a-move-in-grid rover (seq instructions))))
