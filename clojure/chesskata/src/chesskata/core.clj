(ns chesskata.core)

(defn- to-numeric
  [coords]
  (let [[x y] (vec (seq coords))]
    [(- (int x) 96) (Integer/parseInt (str y))]))

(defn- to-string [[x y]]
  (str (char (+ x 96)) y))

(defn- out-of-range?
  ([coord]
   (or (< coord 1) (> coord 8)))
  ([x y]
   (or (out-of-range? x) (out-of-range? y))))

(defn- find-start [[x y]]
  (let [next-x (dec x) next-y (dec y)]
    (if (out-of-range? next-x next-y)
      [x y]
      (find-start [next-x next-y]))))

(defn find-diags
  ([coords]
   (let [[x y] (find-start (to-numeric coords))] (find-diags x y [])))
  ([x y acc]
   (if (out-of-range? x y)
     acc
     (find-diags (inc x) (inc y) (conj acc [x y])))))

(defn- find-start-left [[x y]]
  (let [next-x (dec x) next-y (inc y)]
    (if (out-of-range? next-x next-y)
      [x y]
      (find-start-left [next-x next-y]))))

(defn find-diags-left
  ([coords]
   (let [[x y] (find-start-left (to-numeric coords))] (find-diags-left x y [])))
  ([x y acc]
   (if (out-of-range? x y)
     acc
     (find-diags-left (inc x) (dec y) (conj acc [x y])))))

(defn bishop-diagonal
  [bishop1 bishop2]

  (let [b1-diagonals (find-diags bishop1)
        b2-diagonals (find-diags bishop2)
        b1-diags-left (find-diags-left bishop1)
        b2-diags-left (find-diags-left bishop2)]

    (cond
      (= b1-diagonals b2-diagonals)
      [(to-string  (first b1-diagonals)) (to-string (last b1-diagonals))]

      (= b1-diags-left b2-diags-left)
      [(to-string  (first b1-diags-left)) (to-string (last b1-diags-left))]

      :else [bishop1 bishop2])))
