(ns marsrover.core
  (:require [clojure.spec.alpha :as spec]))

(defn- wrap-if-needed
  ([size axis rover]
   (let [current (rover axis) max (- size 1)]
     (cond
       (> current max) (assoc rover axis 0)
       (< current 0) (assoc rover axis max)
       :else rover)))
  ([grid rover]
   (->> rover
        (wrap-if-needed (:w grid) :x)
        (wrap-if-needed (:h grid) :y))))

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

(spec/def ::coord (spec/and int? #(> % -1)))
(spec/def ::x ::coord)
(spec/def ::y ::coord)
(spec/def ::d (spec/and keyword? #(#{:N :E :S :W} %)))
(spec/def ::rover (spec/keys :req-un [::x ::y ::d]))

(spec/def ::grid-size (spec/and int? #(> % 0)))
(spec/def ::h ::grid-size)
(spec/def ::w ::grid-size)
(spec/def ::obstacles (spec/coll-of (spec/keys :req-un [::x ::y])))
(spec/def ::grid (spec/keys :req-un [::w ::h] :opt-un [::obstacles]))

(spec/def ::instructions (spec/and string? #(re-matches #"^[MRL]+$" %)))

(spec/fdef move :args (spec/cat :grid ::grid :rover ::rover :instructions ::instructions))

(defn move [grid rover instructions]
  (let [a-move-in-grid (partial a-move grid)]
    (reduce a-move-in-grid rover (seq instructions))))
