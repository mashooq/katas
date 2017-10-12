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

(defn- forward
  ([grid rover]
   (case (:d rover)
     :N (forward grid rover :y +)
     :E (forward grid rover :x +)
     :S (forward grid rover :y -)
     :W (forward grid rover :x -)))

  ([grid rover coord op]
   (->> (assoc rover coord (op (rover coord) 1))
        (check-obstacles (:obstacles grid) rover)
        (wrap-if-needed grid))))

(defn- turn-right [rover]
  (case (:d rover)
    :N (assoc rover :d :E)
    :E (assoc rover :d :S)
    :S (assoc rover :d :W)
    :W (assoc rover :d :N)))

(defn- a-move [grid rover instr]
  (case instr
    \M (forward grid rover)
    \R (turn-right rover)))

(defn move [grid rover instructions]
  (let [a-move-in-grid (partial a-move grid)]
    (reduce a-move-in-grid rover (seq instructions))))
