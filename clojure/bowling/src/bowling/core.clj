(ns bowling.core)

(defn- spare?[s] (= \/ s))
(defn- strike? [s] (= \X s))
(defn- spare-or-strike? [s] (or (spare? s) (strike? s)))
(defn- miss? [s] (or (= nil s) (= \- s)))

(defn- score-for [s] 
  (cond 
     (spare-or-strike? s)  10
     (miss? s)  0 
     :else (read-string (str s))))

(defn- score-roll [this-roll rem-rolls]
  (cond 
    (strike? this-roll) (+ 10 (score-for (first rem-rolls)) (score-for (first (rest rem-rolls))))
    (spare? this-roll) (+ 10 (score-for (first rem-rolls)))
    (spare? (first rem-rolls)) 0
    :else (score-for this-roll)))

(defn- score-rolls [acc rolls]
  (if (seq rolls)  
    (let [running-score (+ acc (score-roll (first rolls) (rest rolls)))]
      (score-rolls running-score (rest rolls)))
    acc))

(defn- expand-strikes [rolls]
  (seq (reduce str  (map #(if  (strike? %) "X-"  (str %)) (seq rolls)))))

(defn- deduct-extra-rolls [score rolls]
  (- score  (score-rolls 0 (drop 20 (expand-strikes rolls)))))

(defn score [rolls] 
  (deduct-extra-rolls (score-rolls 0 (seq rolls)) rolls))
