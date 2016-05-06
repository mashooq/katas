(ns bowling.core)

(defn score-for [s] 
  (cond 
     (= \X s) 10
     (or (= nil s) (= \- s))  0 
     (= \/ s) 10 
     :else (read-string (str s))))

(defn- score-roll [this-roll rem-rolls]
  (cond 
    (= \X this-roll) (+ 10 (score-for (first rem-rolls)) (score-for (first (rest rem-rolls))))
    (= \/ this-roll) (+ 10 (score-for (first rem-rolls)))
    (= \/ (first rem-rolls)) 0
    :else (score-for this-roll)))

(defn- score-rolls [acc rolls]
  (if (seq rolls)  
    (let [running-score (+ acc (score-roll (first rolls) (rest rolls)))]
      (score-rolls running-score (rest rolls)))
    acc))

(defn- expand-strikes [rolls]
  (seq (reduce str  (map #(if  (= \X %) "X-"  (str %)) (seq rolls)))))

(defn- deduct-extra-rolls [score rolls]
  (- score  (score-rolls 0 (drop 20 (expand-strikes rolls)))))

(defn score [rolls] 
  (deduct-extra-rolls (score-rolls 0 (seq rolls)) rolls))
