(ns marsrover.core)

(def position (atom {}))
(def nav-grid (atom {}))

(def turns
  {:N {:right :E :left :W}
   :E {:right :S :left :N}
   :S {:right :W :left :E}
   :W {:right :N :left :S}})

(def moves
  {:backward
   {:N {:axis :y :op -}
    :E {:axis :x :op -}
    :S {:axis :y :op +}
    :W {:axis :x :op +}}

   :forward
   {:N {:axis :y :op +}
    :E {:axis :x :op +}
    :S {:axis :y :op -}
    :W {:axis :x :op -}}})

(defn start-position [pos]
  (reset! position pos))

(defn navigation-grid [grid]
  (reset! nav-grid grid))

(defn- turn [direction]
  (swap! position
         #(assoc % :d (get-in turns [(:d %) direction]))))

(defn- wrap-if-needed [next-pos max]
  (cond
    (= next-pos max) 0
    (> 0 next-pos) (- max 1)
    :else next-pos))

(defn- next-position [axis op]
  (let [pos (@position axis)
        max (@nav-grid axis)
        next-pos (wrap-if-needed (op pos 1) max)]
    next-pos))

(defn- obstructed? [axis pos]
  (let [new-pos (dissoc (assoc @position axis pos) :d)]
    (some #(= new-pos %) (:obstacles @nav-grid))))

(defn- make-move [direction]
  (let [{axis :axis op :op} ((moves direction) (:d @position))
        next-pos (next-position axis op)]
    (when-not (obstructed? axis next-pos)
      (swap! position #(assoc % axis next-pos)))))

(defn move [commands]
  (doseq [command commands]
    (case command
      \f (make-move :forward)
      \b (make-move :backward)
      \r (turn :right)
      \l (turn :left)))
  @position)
