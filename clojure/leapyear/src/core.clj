(ns core)

(defn leap-year? [year]
  (if (< year 100)
    (zero? (mod year 4))
    (if (zero? (mod year 100))
      (zero? (mod year 400))
      (zero? (mod year 4)))))
