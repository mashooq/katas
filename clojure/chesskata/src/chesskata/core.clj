(ns chesskata.core)

(defn- to-numeric
  [coords]
  (let [[x y] (vec (seq coords))]
    [(- (int x) 96) (Integer/parseInt (str y))]))

(defn- to-string [x y]
  (str (char (+ x 96)) y))

(defn- out-of-range?
  ([coord]
   (or (< coord 1) (> coord 8)))
  ([x y]
   (or (out-of-range? x) (out-of-range? y))))

(defn- find-start
  ([x y start-fn]
   (let [next-x (dec x) next-y (start-fn y)]
     (if (out-of-range? next-x next-y)
       [x y]
       (find-start next-x next-y start-fn))))
  ([[x y] start-fn]
   (find-start x y start-fn)))

(defn find-diags
  ([coords start-fn end-fn]
   (let [[x y] (find-start (to-numeric coords) start-fn)] (find-diags x y [] end-fn)))
  ([x y acc end-fn]
   (if (out-of-range? x y)
     acc
     (find-diags (inc x) (end-fn y) (conj acc (to-string x y)) end-fn))))

(defn bishop-diagonal
  [bishop1 bishop2]

  (let [b1-diagonals-right (find-diags bishop1 dec inc)
        b2-diagonals-right (find-diags bishop2 dec inc)
        b1-diags-left (find-diags bishop1 inc dec)
        b2-diags-left (find-diags bishop2 inc dec)]

    (cond
      (= b1-diagonals-right b2-diagonals-right)
      [(first b1-diagonals-right) (last b1-diagonals-right)]

      (= b1-diags-left b2-diags-left)
      [(first b1-diags-left) (last b1-diags-left)]

      :else [bishop1 bishop2])))
